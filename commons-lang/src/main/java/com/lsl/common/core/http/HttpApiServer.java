package com.lsl.common.core.http;

import com.alibaba.fastjson.JSON;
import com.lsl.common.jvm.management.JvmUtils;
import com.lsl.domain.User;
import com.lsl.common.util.LogHome;
import com.lsl.common.core.PerformanceWatcher;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * 自定义轻量级Http服务
 */
public class HttpApiServer {

    public static void main(String[] args) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(8080);
        HttpServer server = HttpServer.create(addr, 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        int vmPid = JvmUtils.getVMPid();
        LogHome.info("server is listening on port 8080 进程号：" + vmPid);
    }
}

class RequestHandler implements HttpHandler {

    public void handle(HttpExchange exchange) {
        PerformanceWatcher watcher = PerformanceWatcher.getInstance();
        watcher.start();
        String method = exchange.getRequestMethod();
        URI requestURI = exchange.getRequestURI();
        String uri = requestURI.getPath();
        LogHome.info(method + " " + "接口调用地址[" + uri + "]");

        HttpResponse response = new HttpResponse(HttpResponse.HTTP_OK);
        OutputStream os = exchange.getResponseBody();

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=utf-8");
        try {
            if (uri.contains("api/service")) {
                exchange.sendResponseHeaders(200, 0);
                Map<String, String> params = queryParams(requestURI.getQuery());
                LogHome.info("request params " + JSON.toJSONString(params));
                response.setMsg(HttpResponse.msg(HttpResponse.HTTP_OK));
                if(uri.endsWith("system")){
                    response.setData(JvmUtils.getSystemInfo());
                }else {
                    response.setData(User.instance());
                }


            } else {
                exchange.sendResponseHeaders(404, 0);
                response.setCode(HttpResponse.HTTP_NOT_FOUND);
                response.setMsg(HttpResponse.msg(HttpResponse.HTTP_NOT_FOUND));;
            }
            os.write(JSON.toJSONString(response).getBytes());
        } catch (Exception e) {
            LogHome.error("exception " + e.getMessage(), e);
            response.setCode(HttpResponse.HTTP_INTERNAL_ERROR);
            response.setMsg(HttpResponse.msg(HttpResponse.HTTP_INTERNAL_ERROR));
            try {
                os.write(JSON.toJSONString(response).getBytes());
            } catch (IOException var) {
                LogHome.error("exception " + var.getMessage(), var);
            }
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                LogHome.error("exception " + e.getMessage(), e);
            }
        }
        watcher.watch();

        // String reLoadClass = params.get("reLoadClass");
        // if ("1".equals(reLoadClass)) {
        // try {
        // Class<?> clazz = JavaCompiler.buildClass(
        // "com.lsl.domain.User",
        // "/Users/apple/User.java");
        //
        // Object object = clazz.newInstance();
        //
        // User user = new User();
        //
        // //此处只能通过反射或者字节码工具处理方法调用 asm javassist
        // String msg = "当前线程和类加载器类对象是否相等："
        // + clazz.equals(user.getClass())
        // + "，当前线程new关键字类加载器："
        // + user.getClass().getClassLoader().getClass().getName()
        // + "，自定义编译器类加载器："
        // + clazz.getClassLoader().getClass().getName();
        // LogHome.1并发包(msg);
        //
        // os.write(object.toString().getBytes());
        //
        // } catch (Exception e) {
        // LogHome.1并发包(e.getMessage());
        // }
        // }

    }

    public Map<String, String> queryParams(String queryParams) {
        Map<String, String> params = new HashMap<>();
        if (queryParams != null && !"".equals(queryParams)) {
            String[] pairs = queryParams.split("&");
            for (String paramPair : pairs) {
                String[] var = paramPair.split("=");
                params.put(var[0], var[1]);
            }
        }
        return params;
    }
}