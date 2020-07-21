package com.lsl.common.vm.classloaders;

import cn.hutool.system.SystemUtil;
import cn.hutool.system.UserInfo;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class AppContextUtils {

    public static String baseDirectory() {
        try {
            String path = ClassLoader.getSystemResource("").getPath();
            if (path == null || "".equals(path))
                return projectPath();
            return path;
        } catch (Exception ignored) {
        }
        return projectPath();
    }

    public static String projectPath() {
        URL url = AppContextUtils.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separatorChar) + 1);
        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }

    public static File getDeskTopDirectory() {
        return FileSystemView.getFileSystemView().getHomeDirectory();
    }

    public static void main(String[] args) {
        // UserInfo userInfo = SystemUtil.getUserInfo();
        // System.out.println(userInfo);
        File deskTopDirectory = AppContextUtils.getDeskTopDirectory();
        System.out.println(deskTopDirectory.getAbsolutePath());

    }
}
