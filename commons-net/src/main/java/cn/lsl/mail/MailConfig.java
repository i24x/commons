package cn.lsl.mail;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * 描述:
 * </p>
 *
 * @author yangcao
 * @version v1.0
 * @date 2020/8/2 9:39
 */
public class MailConfig {
    private String userName;
    private String password;

    public static void main(String[] args) {
        MailConfig mailConfig = getMailConfig();
        System.out.println(mailConfig);

    }

    public static MailConfig getMailConfig() {
        MailConfig mailConfig = new MailConfig();
        Properties properties = new Properties();
        try {
            properties.load(MailConfig.class.getClassLoader().getResourceAsStream("email.properties"));
            mailConfig.setUserName(properties.getProperty("server.user"));
            mailConfig.setPassword(properties.getProperty("server.password"));
        } catch (IOException e) {
            throw new RuntimeException("加载邮件配置服务失败", e);
        }
        return mailConfig;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MailConfig{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + '}';
    }
}
