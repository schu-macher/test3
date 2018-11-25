package cu.tissca.something;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    MyRepo myRepo(){
        return new MyRepo();
    }
}
