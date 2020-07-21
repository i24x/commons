package com.lsl.common.vm.classloaders.load;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class JavaSource extends SimpleJavaFileObject {
    final String code;

    public JavaSource(String fullClassName, String resourceCode) {
        super(URI.create("string:///" + fullClassName.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = resourceCode;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return this.code;
    }
}
