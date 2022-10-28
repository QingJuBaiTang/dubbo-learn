package org.example;

import org.apache.dubbo.config.*;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class Provider {
    public static void main(String[] args) {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("demo");

        ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<>();
        demoServiceConfig.setInterface(DemoService.class);
        demoServiceConfig.setRef(new DemoServiceImpl());

        DubboBootstrap.getInstance()
                .application("demo-provider")
                .registry(new RegistryConfig("nacos://xxxxxxxxxxxxxxxxx"))
                .protocol(protocol)
                .service(demoServiceConfig)
                .start()
                .await();
    }
}
