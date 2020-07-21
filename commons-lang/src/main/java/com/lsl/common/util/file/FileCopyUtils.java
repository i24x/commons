package com.lsl.common.util.file;

import com.lsl.common.vm.mbean.VmConfig;

import java.io.*;
import java.nio.file.Files;

public class FileCopyUtils {

    public static void main(String[] args) {
        String userHome = VmConfig.getUserHome();
        String rfile = userHome + File.separator + "rfile.txt";
        File file = new File(rfile);
        try (FileInputStream fileReader = new FileInputStream(file);
            InputStreamReader ir = new InputStreamReader(fileReader, "UTF-8");
            BufferedReader br = new BufferedReader(ir)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        File wFile = new File(userHome + File.separator + "wfile.txt");
        if (wFile.exists()) {
            wFile.delete();
        }

        // try {
        // wFile.createNewFile();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // try (FileInputStream fi = new FileInputStream(file);
        // FileOutputStream fo = new FileOutputStream(wFile)) {
        // FileChannel channel = fi.getChannel();
        // channel.transferTo(0,channel.size(),fo.getChannel());
        // } catch (Exception e) {
        // System.err.println(e.getMessage());
        // }

        try {
            Files.copy(file.toPath(), wFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
