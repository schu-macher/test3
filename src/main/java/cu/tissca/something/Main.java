package cu.tissca.something;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        final MyRepo bean = context.getBean(MyRepo.class);
        bean.foo();
        context.close();
    }
}
