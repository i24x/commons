package com.lsl.common.jvm.classloader.load;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.lsl.common.exception.BizRuntimeException;
import com.lsl.common.jvm.classloader.PathUtil;
import com.lsl.common.jvm.reflect.ReflectionUtils;
import com.lsl.common.util.LogHome;

public class JavaCompiler {

    public static Class<?> buildClass(String fullClassName, String soureFilePath) {
        return buildClass(fullClassName, soureFilePath, StandardCharsets.UTF_8.name(), PathUtil.targetDir());
    }

    public static Class<?> buildClass(String fullClassName, String soureFilePath, String binFilePath) {
        return buildClass(fullClassName, soureFilePath, StandardCharsets.UTF_8.name(), binFilePath);
    }

    public static Class<?> buildClass(String fullClassName, String sourcePath, String charsetName, String binFilePath) {
        LogHome.info("类全路径[" + fullClassName + "],编译输出路径[" + binFilePath + "],源码文件根路径[" + sourcePath + "],编码集["
            + charsetName + "]");

        String code;
        try {
            code = FileUtils.readFileToString(FileUtils.getFile(sourcePath), charsetName);
        } catch (IOException e) {
            throw new BizRuntimeException("无法定位到源文件：" + sourcePath);
        }
        javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        List<JavaFileObject> fileObjs = new ArrayList<>();

        fileObjs.add(new JavaSource(fullClassName, code));

        List<String> options = new ArrayList<>();
        options.add("-classpath");
        StringBuilder sb = new StringBuilder();
        URLClassLoader urlClassLoader = (URLClassLoader)Thread.currentThread().getContextClassLoader();

        for (URL url : urlClassLoader.getURLs()) {
            sb.append(url.getFile()).append(File.pathSeparator);
        }
        options.add(sb.toString());
        options.add("-d");
        options.add(binFilePath);
        LogHome.info("更多编译命令参数参考[javac]命令");

        boolean isok = compiler.getTask(null, fileManager, null, options, null, fileObjs).call();
        if (isok) {
            return JavaFileClassLoader.getInstance().findClass(fullClassName);
        }
        return null;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Class<?> cls = JavaCompiler.buildClass("com.lsl.domain.HelloWordBean",
            PathUtil.getProjectRootPath() + "/HelloWordBean.java");
        Object bean = cls.newInstance();
        ReflectionUtils.setFieldValue(bean, "var", "cat");
        System.out.println(JSON.toJSONString(bean));

    }
}
