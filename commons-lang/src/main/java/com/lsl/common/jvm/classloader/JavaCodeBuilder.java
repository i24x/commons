package com.lsl.common.jvm.classloader;

import com.lsl.common.exception.BizRuntimeException;
import com.lsl.common.util.LogHome;

import javax.tools.*;
import javax.tools.JavaCompiler;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class JavaCodeBuilder {

    private static final String CLASS_NAME = "TClass";
    private static final String TEMP_PACKAGE_PATH = "com.lsl.code";

    private static JavaCodeBuilder builder = new JavaCodeBuilder();

    private JavaCodeBuilder() {

    }

    public static JavaCodeBuilder build() {
        return builder;
    }

    public Object run(List<String> imports, String packageName, String className, String code, String retunObj) {

        StringBuilder sb = new StringBuilder();
        // package
        sb.append("package ").append(packageName).append(";\n");

        // imports
        if (imports != null) {
            for (String anImport : imports) {
                sb.append("import ").append(anImport).append(";\n");
            }
        }

        // class begin
        sb.append("public class " + className + " {\n");

        sb.append("public Object eval() {\n");
        // code
        sb.append(code);
        sb.append("\n");

        // return
        sb.append("return ");
        if (retunObj == null) {
            sb.append("null");
        } else {
            sb.append(retunObj).append(";");
        }
        sb.append("\n");

        sb.append("}");

        sb.append("\n");
        // class end
        sb.append("}");

        String wholeClassName = packageName + "." + className;

        Class<?> clazz = compile(wholeClassName, sb.toString());
        try {
            Object instance = clazz.newInstance();
            Method m = clazz.getMethod("eval");
            return m.invoke(instance);
        } catch (Exception e) {
            throw new BizRuntimeException("构建字节码文件失败", e);
        }

    }

    /**
     * 装载字符串成为java可执行文件
     *
     * @param className
     *            className
     * @param code
     *            javaCodes
     * @return Class
     */
    private Class<?> compile(String className, String code) {
        LogHome.warn("ToolProvider.getSystemJavaCompiler需要导入jar://javahome/lib/tools.jar!/");
        javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        JavaSourceObject srcObject = new JavaSourceObject(className, code);

        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
        String flag = "-d";
        String outDir = "";
        try {
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            outDir = classPath.getAbsolutePath() + File.separator;
            LogHome.info("class file outDir:" + outDir);
        } catch (URISyntaxException e) {
            LogHome.error("", e);
        }
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
        boolean result = task.call();
        if (result == true) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BizRuntimeException("构建字节码文件失败", e);
            }
        } else {
            throw new BizRuntimeException("构建字节码文件失败");
        }
    }

    private static class JavaSourceObject extends SimpleJavaFileObject {
        private String content;

        JavaSourceObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }

    public static void main(String[] args) {
        StringBuilder code = new StringBuilder();
        code.append("System.out.println(\"hello world\");");
        code.append("System.out.println(\"3+5=\"+(3+5));");
        Object rst =
            JavaCodeBuilder.build().run(null, TEMP_PACKAGE_PATH, CLASS_NAME, code.toString(), "\"执行eval代码片段\"");
        if (rst != null) {
            System.out.println(rst.toString());
        }

    }
}
