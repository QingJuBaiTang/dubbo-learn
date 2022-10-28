package org.example;

import java.util.concurrent.TimeUnit;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHi() throws InterruptedException {
        System.out.println("provider print hi");
        TimeUnit.SECONDS.sleep(3);
        return "hi";
    }
}
