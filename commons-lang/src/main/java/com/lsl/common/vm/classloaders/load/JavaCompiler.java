package com.lsl.common.vm.classloaders.load;

import com.lsl.common.util.LogHome;
import com.lsl.common.vm.classloaders.AppContextUtils;
import org.apache.commons.io.FileUtils;

import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JavaCompiler {

    public static Class<?> buildClass(String fullClassName, String soureFilePath)
        throws IOException, ClassNotFoundException {
        return buildClass(fullClassName, soureFilePath, StandardCharsets.UTF_8.name(), AppContextUtils.baseDirectory());
    }

    public static Class<?> buildClass(String fullClassName, String soureFilePath, String binFilePath)
        throws IOException, ClassNotFoundException {

        return buildClass(fullClassName, soureFilePath, StandardCharsets.UTF_8.name(), binFilePath);
    }

    public static Class<?> buildClass(String fullClassName, String sourceFilePath, String charsetName,
        String binFilePath) throws IOException, ClassNotFoundException {
        try {
            LogHome.info("类全路径[" + fullClassName + "],编译输出路径[" + binFilePath + "],源码文件根路径[" + sourceFilePath + "],编码集["
                + charsetName + "]");

            String code = FileUtils.readFileToString(FileUtils.getFile(sourceFilePath), charsetName);

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
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void main(String[] args)
        throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass0 = JavaCompiler.buildClass("com.lsl.domain.User", "/Users/apple/User.java");
        Object o0 = aClass0.newInstance();

        Class<?> aClass1 = JavaCompiler.buildClass("com.lsl.domain.User", "/Users/apple/User.java");
        Object o1 = aClass1.newInstance();

        System.out.println(o0.equals(o1));
    }
}
