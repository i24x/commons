package com.lsl.common.util.file;

import java.io.*;
import java.util.function.Function;

public class FileUtils {
    public static String dirSeparator() {
        return File.separator;
    }

    /**
     * 创建文件 java
     *
     * @param filePath
     *            文件路径（包括文件名）
     * @param force
     *            是否强制创建（移除之前的创建）
     * @return
     * @throws IOException
     */
    public static File makeFile(String filePath, Boolean force) throws IOException {
        return makeFile(new File(filePath), false);
    }

    /**
     * 创建文件
     *
     * @param file
     *            带创建的文件
     * @param force
     *            是否强制创建（移除之前的创建）
     * @return
     * @throws IOException
     */
    public static File makeFile(File file, Boolean force) throws IOException {
        // 文件为空则不执行操作
        if (file == null) {
            return null;
        }
        if (file.exists()) {// 文件已存在
            if (force) {// 如果强制创建
                file.delete();
                file.createNewFile();
                return file;
            } else {// 不强制创建则不执行操作
                return null;
            }
        } else {// 文件不存在
            if (!file.getParentFile().exists()) {// 上级如果不存在则创建上级目录
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            return file;
        }
    }

    public static String readText(Function<BufferedReader, String> handler, String path, String charSet) {
        String rst = "";
        BufferedReader br = null;
        try {
            System.out.println("--生成更新用户邮箱信息SQL开始");
            InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(path);
            br = new BufferedReader(new InputStreamReader(is, charSet));
            rst = handler.apply(br);
        } catch (IOException e) {
            System.out.println("文件解析失败");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("--生成更新用户邮箱信息SQL结束");
        return rst;
    }

    public static void main(String[] args) {

        String sqlText = FileUtils.readText(reader -> {
            StringBuilder rst = new StringBuilder();
            try {
                String l = reader.readLine();
                int count = 0;
                while (l != null) {
                    if (!l.startsWith("TLR_NO")) {
                        String[] members = l.split("\\s");
                        if (members.length > 2) {
                            String sql = String.format(
                                "UPDATE  ICCS_SCUBE.TLR_INFO SET EMAIL" + " ='%1$s' WHERE TLR_NO = '%2$s';", members[2],
                                members[0]);
                            rst.append(sql).append("\n");
                            count++;
                        }
                    }
                    l = reader.readLine();
                }
                System.out.println("--共计" + count + "条sql语句");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rst.toString();
        }, "file/mail.txt", "GB2312");

        System.out.println(sqlText);

    }

    public static final String test(int num) {

        if (num == 0) {
            for (int i = 0; i < 10; i++) {
                System.out.println("T" + i);
            }
        }
        return "F";
    }
}
