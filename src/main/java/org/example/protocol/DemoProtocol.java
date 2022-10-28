package org.example.protocol;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.RemotingServer;
import org.apache.dubbo.remoting.http.HttpBinder;
import org.apache.dubbo.rpc.ProtocolServer;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.protocol.AbstractProxyProtocol;

import java.lang.reflect.Proxy;

public class DemoProtocol extends AbstractProxyProtocol {
    private HttpBinder httpBinder;

    @SuppressWarnings("unused")
    public void setHttpBinder(HttpBinder httpBinder) {
        this.httpBinder = httpBinder;
    }

    @Override
    protected <T> Runnable doExport(T impl, Class<T> type, URL url) throws RpcException {
        String addr = getAddr(url);

        ProtocolServer protocolServer = serverMap.get(addr);
        if (protocolServer == null) {
            RemotingServer remotingServer = httpBinder.bind(url, new DemoHttpHandler());
            serverMap.put(addr, new ProxyProtocolServer(remotingServer));
        }

        return null;
    }

    @Override
    protected <T> T doRefer(Class<T> type, URL url) throws RpcException {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new DemoProxyClient());
    }

    @Override
    public int getDefaultPort() {
        return 8080;
    }
}
