package com.lsl.common.jvm.management;

import java.io.File;

public class VmConfig {

    public static final String SUN_CPU_ISALIST = "sun.cpu.isalist";
    public static final String FTP_NONPROXYHOSTS = "ftp.nonProxyHosts";
    public static final String SOCKSNONPROXYHOSTS = "socksNonProxyHosts";
    public static final String SUN_IO_UNICODE_ENCODING = "sun.io.unicode.encoding";
    public static final String SUN_CPU_ENDIAN = "sun.cpu.endian";
    public static final String JAVA_VENDOR_URL_BUG = "java.vendor.url.bug";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String JAVA_VENDOR = "java.vendor";
    public static final String SUN_BOOT_CLASS_PATH = "sun.boot.class.path";
    public static final String JAVA_EXT_DIRS = "java.ext.dirs";
    public static final String JAVA_VERSION = "java.version";
    public static final String JAVA_VM_INFO = "java.vm.1并发包";
    public static final String AWT_TOOLKIT = "awt.toolkit";
    public static final String USER_LANGUAGE = "user.language";
    public static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";
    public static final String SUN_JAVA_COMMAND = "sun.java.command";
    public static final String JAVA_HOME = "java.home";
    public static final String SUN_ARCH_DATA_MODEL = "sun.arch.data.model";
    public static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";
    public static final String JAVA_CLASS_PATH = "java.class.path";
    public static final String USER_NAME = "user.name";
    public static final String FILE_ENCODING = "file.encoding";
    public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
    public static final String JAVA_AWT_PRINTERJOB = "java.awt.printerjob";
    public static final String USER_TIMEZONE = "user.timezone";
    public static final String USER_HOME = "user.home";
    public static final String HTTP_NONPROXYHOSTS = "http.nonProxyHosts";
    public static final String OS_VERSION = "os.version";
    public static final String SUN_MANAGEMENT_COMPILER = "sun.management.compiler";
    public static final String JAVA_SPECIFICATION_NAME = "java.specification.name";
    public static final String JAVA_CLASS_VERSION = "java.class.version";
    public static final String JAVA_LIBRARY_PATH = "java.library.path";
    public static final String SUN_JNU_ENCODING = "sun.jnu.encoding";
    public static final String OS_NAME = "os.name";
    public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
    public static final String LINE_SEPARATOR = "line.separator";
    public static final String JAVA_ENDORSED_DIRS = "java.endorsed.dirs";
    public static final String OS_ARCH = "os.arch";
    public static final String JAVA_AWT_GRAPHICSENV = "java.awt.graphicsenv";
    public static final String JAVA_RUNTIME_VERSION = "java.runtime.version";
    public static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";
    public static final String USER_DIR = "user.dir";
    public static final String USER_COUNTRY = "user.country";
    public static final String SUN_JAVA_LAUNCHER = "sun.java.launcher";
    public static final String SUN_OS_PATCH_LEVEL = "sun.os.patch.level";
    public static final String JAVA_VM_NAME = "java.vm.name";
    public static final String FILE_ENCODING_PKG = "file.encoding.pkg";
    public static final String PATH_SEPARATOR = "path.separator";
    public static final String JAVA_VM_VENDOR = "java.vm.vendor";
    public static final String JAVA_VENDOR_URL = "java.vendor.url";
    public static final String GOPHERPROXYSET = "gopherProxySet";
    public static final String SUN_BOOT_LIBRARY_PATH = "sun.boot.library.path";
    public static final String JAVA_VM_VERSION = "java.vm.version";
    public static final String JAVA_RUNTIME_NAME = "java.runtime.name";

    public static void main(String[] args) {
        System.out.println(System.getProperty(USER_DIR));
        System.out.println(System.getProperty(USER_HOME));
        System.out.println(System.getProperty(FILE_SEPARATOR));
        System.out.println(System.getProperty(JAVA_HOME));
        System.out.println(File.separator);
    }

    public static String getUserHome() {
        return System.getProperty(USER_HOME);
    }

    public static String getProjectHome() {
        return System.getProperty(USER_DIR);
    }

    public static String getJavaHome() {
        return System.getProperty(JAVA_HOME);
    }
}
