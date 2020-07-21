package com.lsl.common.mail;

import com.lsl.domain.constant.TrueOrFalse;
import com.lsl.common.util.LogHome;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail163 {
    private static final String account = "cyang198906@163.com";
    private static final String authPwd = "yc535689";

    public static boolean sendEmail(String title, String content, String... destnations) {
        boolean success = true;
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.163.com");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 第三方客户端授权密码和登录密码不一样
                final String account_prefix = account.split("@")[0];
                return new PasswordAuthentication(account_prefix, authPwd);
            }
        });
        session.setDebug(false);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(account));
            msg.setSubject(title);
            msg.setContent(content, "text/html;charset=utf-8");
            if (destnations != null && destnations.length > 0) {
                msg.setRecipients(RecipientType.TO, InternetAddress.parse(String.join(",", destnations)));
                // msg.setRecipients(RecipientType.CC, InternetAddress.parse(account));
            } else {
                throw new IllegalArgumentException("目标邮件地址不可为空");
            }
            Transport.send(msg);
        } catch (MessagingException e) {
            LogHome.error(e.getMessage());
            success = false;
        }
        return success;
    }

    public static void main(String[] args) throws MessagingException {

        String title = "邮件模板";
        String content = MailTemplate.getContent("Rose");
        boolean success = Mail163.sendEmail(title, content, "610039525@qq.com");
        LogHome.info(String.format("邮件发送%1$s", TrueOrFalse.match(success).success()));

    }
}