package org.example;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class Consumer {
    public static void main(String[] args) {
        ReferenceConfig<DemoService> demoServiceReference = new ReferenceConfig<>();
        demoServiceReference.setInterface(DemoService.class);
        demoServiceReference.setCheck(false);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application("demo-consumer")
                .registry(new RegistryConfig("nacos://xxxxxxxxxxxxxxxxx"))
                .reference(demoServiceReference)
                .start();

        while (true) {
            try {
                demoServiceReference.get().sayHi();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
