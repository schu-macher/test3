package cu.tissca.something;

import cu.tissca.something.repos.MyProductRepository;
import cu.tissca.something.repos.MyRepo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("h2");
        context.register(MyConfig.class);
        context.refresh();
        final MyRepo bean = context.getBean(MyRepo.class);
        bean.foo();
        final MyProductRepository bean1 = context.getBean(MyProductRepository.class);
        final MyProduct s = new MyProduct();
        s.setName("shoe1");
        bean1.save(s);
        context.close();
    }
}