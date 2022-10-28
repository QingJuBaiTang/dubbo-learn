package org.example.protocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DemoProxyClient implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (true) {
            throw new NullPointerException();
        }

        return "";
    }
}
