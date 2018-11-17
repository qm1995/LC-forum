package com.lc.forum.system.email.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qiumin
 * @create 2018/11/9 20:57
 * @desc
 **/
@Component
public class EmailConfig {

    @Value("${stmp.host}")
    private String host;

    @Value("${stmp.account}")
    private String account;

    @Value("${stmp.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String isAuth;

    @Value("${mail.smtp.timeout}")
    private String outTime;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
