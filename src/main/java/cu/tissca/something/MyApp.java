package cu.tissca.something;

import cu.tissca.something.repos.MyProductRepository;

public class MyApp {
    private MyProductRepository myProductRepository;

    public MyApp(MyProductRepository myProductRepository){
        this.myProductRepository = myProductRepository;
    }
}
