package com.lsl.common.jvm.classloader.load;

import com.lsl.common.util.LogHome;
import com.lsl.common.jvm.classloader.PathUtil;
import org.apache.commons.io.FileUtils;

import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;

public class JavaFileClassLoader extends ClassLoader {

    private static JavaFileClassLoader loader = new JavaFileClassLoader();

    public static JavaFileClassLoader getInstance() {
        return new JavaFileClassLoader();
    }

    // 注意方法 破坏委派
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    // findClass，defineClass会回调loadClass
    @Override
    protected Class<?> findClass(String name) {
        return this.findClass(PathUtil.targetDir(), name);
    }

    private Class<?> findClass(String fileRootPath, String fullClassName) {
        Class<?> loadedClass = super.findLoadedClass(fullClassName);
        if (loadedClass != null) {
            LogHome.info("类[" + fullClassName + "]已被加载");
            return loadedClass;
        }
        byte[] codeBytes = null;

        StringBuffer url = new StringBuffer(fileRootPath);

        if (!fileRootPath.endsWith("/")) {
            url.append("/");
        }
        url.append(fullClassName.replace('.', '/') + JavaFileObject.Kind.CLASS.extension);

        File file = FileUtils.getFile(url.toString());

        try {
            codeBytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            LogHome.info(e.getMessage());
        }
        return super.defineClass(fullClassName, codeBytes, 0, codeBytes.length);
    }
}