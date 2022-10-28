package org.example.protocol;

import org.apache.dubbo.remoting.http.HttpHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DemoHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getOutputStream().println("simple");
    }
}
