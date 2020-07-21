package com.lsl.commons.lang.utils.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * 轻量级HttpServer
 */
public class DefaultHttpServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(addr, 0);
        server.createContext("/", new ContextRootHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 8080");
    }
}

class ContextRootHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        URI requestURI = exchange.getRequestURI();
        String uri = requestURI.getPath();
        System.out.println("URI->" + uri);
        OutputStream os = exchange.getResponseBody();
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        if (StringUtils.endsWith(uri, "getHeader")) {
            exchange.sendResponseHeaders(200, 0);
            Headers requestHeaders = exchange.getRequestHeaders();
            Set<String> keySet = requestHeaders.keySet();
            Iterator<String> iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List values = requestHeaders.get(key);
                String s = key + " = " + values.toString() + "\n";
                os.write(s.getBytes());
            }
        } else if (StringUtils.endsWith(uri, "getURI")) {
            exchange.sendResponseHeaders(200, 0);
            os.write(uri.getBytes());
        } else {
            exchange.sendResponseHeaders(404, 0);
        }
        os.close();
    }
}