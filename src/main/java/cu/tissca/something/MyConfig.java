package cu.tissca.something;

import cu.tissca.something.repos.MyProductRepository;
import cu.tissca.something.repos.MyRepo;
import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@EnableJpaRepositories(basePackages = "cu.tissca.something.repos")
public class MyConfig {
    private static final Logger LOGGER = Logger.getLogger(MyConfig.class.getName());

    @Bean
    MyRepo myRepo(){
        return new MyRepo();
    }


    @Bean(name = "dataSource")
    @Profile("postgres")
    public DataSource dataSourcePg() {
        LOGGER.info("Using postgres");
        PGPoolingDataSource pgPoolingDataSource = new PGPoolingDataSource();
        pgPoolingDataSource.setServerName("localhost");
        pgPoolingDataSource.setDatabaseName("schu-macher");
        pgPoolingDataSource.setUser("postgres");
        pgPoolingDataSource.setPassword("appollox901");
        pgPoolingDataSource.setPortNumber(32768);
        return pgPoolingDataSource;
    }

    @Bean(name = "dataSource")
    @Profile("h2")
    public DataSource dataSourceH2() {
        LOGGER.info("Using H2");
        org.h2.jdbcx.JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:~/test");
        return ds;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("demox");
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        return emfb;
    }

    @Bean
    @Profile("postgres")
    public JpaVendorAdapter jpaVendorAdapterPG(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setShowSql(true);
        return adapter;
    }

    @Bean
    @Profile("h2")
    public JpaVendorAdapter jpaVendorAdapterH2(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        return adapter;
    }


    @Bean
    public MyApp myApp(MyProductRepository myProductRepository){
        return new MyApp(myProductRepository);
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
