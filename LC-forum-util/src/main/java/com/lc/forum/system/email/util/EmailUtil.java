package com.lc.forum.system.email.util;

import com.lc.forum.system.email.util.config.EmailConfig;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author qiumin
 * @create 2018/11/9 21:10
 * @desc
 **/
public class EmailUtil {

    private static final EmailConfig CONFIG = new EmailConfig();

    private static final String DEFAULT_MAIL_SUBJECT = "来自lc-forum的邮件注册码";
    /**
     * 获取发送邮件的类
     * @return
     */
    private static JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(CONFIG.getHost());
        javaMailSender.setUsername(CONFIG.getAccount());
        javaMailSender.setPassword(CONFIG.getPassword());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", CONFIG.getIsAuth());
        properties.put("mail.smtp.timeout", CONFIG.getOutTime());
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    public static void sendEmail(String toUser,String content,String subject){
        JavaMailSenderImpl mailSender = getMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(CONFIG.getAccount());
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setTo(toUser);

        mailSender.send(mailMessage);
    }

    public static void sendMail(String toUser,String content){
        sendEmail(toUser,content,DEFAULT_MAIL_SUBJECT);
    }
}
