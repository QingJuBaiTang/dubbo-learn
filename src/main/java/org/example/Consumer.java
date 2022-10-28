package org.example;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.ReferenceConfigBase;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.util.ArrayList;
import java.util.Collection;

public class Consumer {
    public static void main(String[] args) {
        ReferenceConfig<DemoService> demoServiceReference = new ReferenceConfig<>();
        demoServiceReference.setInterface(DemoService.class);
        demoServiceReference.setTimeout(1000);
        demoServiceReference.setRetries(0);
        demoServiceReference.setCheck(false);

        ReferenceConfig<DemoService> demoServiceReference02 = new ReferenceConfig<>();
        demoServiceReference02.setInterface(DemoService.class);
        demoServiceReference02.setTimeout(5000);
        demoServiceReference02.setRetries(3);
        demoServiceReference02.setCheck(false);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application("demo-consumer")
                .registry(new RegistryConfig("nacos://nacos-server-stable.tsign.cn:8848"))
                .reference(demoServiceReference02)
                .reference(demoServiceReference)
                .start();

        Collection<ReferenceConfigBase<?>> refs = new ArrayList<>();
        refs.add(demoServiceReference);
        refs.add(demoServiceReference02);

        for (ReferenceConfigBase<?> ref : refs) {
            long s = System.currentTimeMillis();
            try {
                DemoService r = (DemoService) ref.get();
                System.out.println("consumer print " + r.sayHi());
                long e = System.currentTimeMillis();
            } catch (Exception e) {
                System.out.println("timeout");
            }
            long c = System.currentTimeMillis() - s;
            System.out.println("cost: " + c);
        }

        System.exit(0);
    }
}
