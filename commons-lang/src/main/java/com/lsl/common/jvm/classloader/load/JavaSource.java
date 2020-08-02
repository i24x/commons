package com.lsl.common.jvm.classloader.load;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class JavaSource extends SimpleJavaFileObject {

    private String code;

    public JavaSource(String fullclassname, String code) {
        super(URI.create("string:///" + fullclassname.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return this.code;
    }
}
