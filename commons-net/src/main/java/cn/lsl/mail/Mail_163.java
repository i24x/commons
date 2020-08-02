package cn.lsl.mail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail_163 {
    public static void main(String[] args) {
        String result = Mail_163.sendEmail("网易邮件服务", "网易邮件服务测试", "610039525@qq.com");

    }

    public static String sendEmail(String mailTitle, String mailContent, String... toUsers) {
        MailConfig mailConfig = MailConfig.getMailConfig();
        return Mail_163.sendEmail(mailConfig.getUserName(), mailConfig.getPassword(), mailTitle, mailContent, toUsers);
    }

    public static String sendEmail(final String userName, final String password, String mailTitle, String mailContent,
        String[] toUsers) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.163.com");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 第三方客户端授权密码和登录密码不一样
                final String fromAddr_prefixx = userName.split("@")[0];
                return new PasswordAuthentication(fromAddr_prefixx, password);
            }
        });
        session.setDebug(true);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userName));
            msg.setSubject(mailTitle);
            msg.setContent("<span style='color:red;margin:0 auto'>" + mailContent + "</span>",
                "text/html;charset=utf-8");
            if (toUsers != null && toUsers.length == 1) {
                msg.setRecipient(RecipientType.TO, new InternetAddress(toUsers[0]));
            } else if (toUsers != null && toUsers.length > 1) {
                msg.setRecipients(RecipientType.TO, InternetAddress.parse(String.join(",", toUsers)));
            } else {
                throw new RuntimeException("目标邮件地址不可为空");
            }
            Transport.send(msg);
            return MailResponseCode.SUCCESS;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
        return MailResponseCode.ERROR;
    }

    static class MailResponseCode {
        public static final String SUCCESS = "200";
        public static final String ERROR = "500";
    }
}
