package org.example;

import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class Provider {
    public static void main(String[] args) {
        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);

        ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<>();
        demoServiceConfig.setInterface(DemoService.class);
        demoServiceConfig.setRef(new DemoServiceImpl());
        // demoServiceConfig.setVersion("1.0.0");

        // 通过DubboBootstrap简化配置组装，控制启动过程
        DubboBootstrap.getInstance()
                .application("demo-provider")
                .registry(new RegistryConfig("nacos://nacos-server-stable.tsign.cn:8848")) // 注册中心配置
                .protocol(protocol)
                .service(demoServiceConfig)
                .start()
                .await();
    }
}
