package com.lc.forum.system.email.util;

import com.lc.forum.system.email.util.config.EmailConfig;
import com.lc.forum.system.logFactory.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author qiumin
 * @create 2018/11/9 21:10
 * @desc
 **/
@Component
@EnableConfigurationProperties(EmailConfig.class)
public class EmailUtil {

    private static final Logger BUSINESS = LogFactory.getBusinessLog();

    @Autowired
    private  EmailConfig config;

    private static final String DEFAULT_MAIL_SUBJECT = "来自lc-forum的邮件注册码";
    /**
     * 获取发送邮件的类
     * @return
     */
    public JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(config.getHost());
        javaMailSender.setUsername(config.getAccount());
        javaMailSender.setPassword(config.getPassword());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", config.getIsAuth());
        properties.put("mail.smtp.timeout", config.getOutTime());
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    public void sendEmail(String toUser,String content,String subject){
        JavaMailSenderImpl mailSender = getMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(config.getAccount());
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setTo(toUser);

        mailSender.send(mailMessage);
        BUSINESS.info("发送邮件成功,接收者邮箱{},发送内容{}",toUser,content);
    }

    public void sendMail(String toUser,String content){
        sendEmail(toUser,content,DEFAULT_MAIL_SUBJECT);
    }
}
