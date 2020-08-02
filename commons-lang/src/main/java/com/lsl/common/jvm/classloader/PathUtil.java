package com.lsl.common.jvm.classloader;

import com.lsl.common.exception.BizRuntimeException;
import com.lsl.common.jvm.management.VmConfig;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.swing.filechooser.FileSystemView;

public class PathUtil {

    public static String targetDir() {
            String path = ClassLoader.getSystemResource("").getPath();
            if (path == null || "".equals(path)) {
                return domainTargetDir();
            }
        return domainTargetDir();
    }

    public static String domainTargetDir() {
        URL url = PathUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath;
        try {
            filePath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new BizRuntimeException("文件编码错误");
        }
        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separatorChar) + 1);
        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }

    public static File getDeskTopDir() {
        return FileSystemView.getFileSystemView().getHomeDirectory();
    }
    public static String getProjectRootPath() {
        return System.getProperty(VmConfig.USER_DIR);
    }

    public static void main(String[] args) {
        File deskTopDirectory = PathUtil.getDeskTopDir();
        System.out.println(deskTopDirectory.getAbsolutePath());
        System.out.println(PathUtil.targetDir());
        System.out.println(PathUtil.domainTargetDir());
        System.getProperties().forEach((k,v)->{
            System.out.println(String.format("%1$s========%2$s",k,v));
        });

    }
}
