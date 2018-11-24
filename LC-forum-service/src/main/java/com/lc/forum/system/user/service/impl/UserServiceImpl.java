package com.lc.forum.system.user.service.impl;

import com.fc.forum.system.model.EmailLog;
import com.fc.forum.system.model.User;
import com.lc.forum.system.builder.MapperCriteriaBuilder;
import com.lc.forum.system.email.util.EmailUtil;
import com.lc.forum.system.mapper.emailLog.dao.EmailLogDAO;
import com.lc.forum.system.mapper.user.dao.UserDAO;
import com.lc.forum.system.user.service.UserService;
import com.lc.forum.system.util.response.ActionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author qiumin
 * @create 2018/10/28 22:23
 * @desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private EmailLogDAO emailLogDAO;


    @Override
    @Transactional
    public ActionResult userRegister(User user) {
        String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pwd);
        String token = user.getEmail()+"@"+UUID.randomUUID().toString().substring(0,31-user.getEmail().length());
        user.setToken(token);
        user.setStatus(1);
        user.setUserLevel(1);
        user.setPoints(0);
        userDAO.insertSelective(user);

        emailUtil.sendRegisterEmail(user.getEmail(),Base64Utils.encodeToString(token.getBytes()));

        EmailLog log = new EmailLog();
        log.setToUser(user.getEmail());
        log.setCreateTime(new Date());
        log.setEmailContent(token);
        emailLogDAO.insertSelective(log);
        return ActionResult.ok(null);
    }

    @Override
    public ActionResult listAllUser() {

        return ActionResult.ok(userDAO.selectAll());
    }

    @Override
    public ActionResult findUser(Integer userId) {
        MapperCriteriaBuilder builder =MapperCriteriaBuilder.instances(User.class);
        builder.addEq("id",userId);
        List<User> users = userDAO.selectByExample(builder.getExample());
        if (users.size() == 0){
            return ActionResult.failureParamter("此用户不存在");
        }
        return ActionResult.ok(users.get(0));
    }

    @Override
    public ActionResult loginUser(String username, String password) {
        MapperCriteriaBuilder builder = MapperCriteriaBuilder.instances(User.class);
        builder.addEq("username",username);
        builder.addEq("password",DigestUtils.md5DigestAsHex(password.getBytes()));
        List<User> users = userDAO.selectByExample(builder.getExample());
        if (users.size() == 0){
            return ActionResult.failureLogin("用户名或密码错误");
        }
        if (users.get(0).getStatus() == 1){
            return ActionResult.failureParamter("您的账号未激活,请查看邮箱邮件尽快激活");
        }
        return ActionResult.ok(users.get(0),"登录成功");
    }

    @Override
    @Transactional
    public ActionResult activatingUser(String activeCode) {
        byte[] bytes = Base64Utils.decodeFromString(activeCode);
        String token = new String(bytes);
        if (!token.contains("@")){
            return ActionResult.failureParamter("激活码不存在");
        }
        MapperCriteriaBuilder builder = MapperCriteriaBuilder.instances(EmailLog.class);
        builder.addEq("token",token);
        List<EmailLog> emailLogs = emailLogDAO.selectByExample(builder.getExample());
        if (emailLogs.isEmpty() || emailLogs.size() > 1){
            return ActionResult.failureParamter("激活码错误");
        }
        EmailLog emailLog = emailLogs.get(0);
        if (System.currentTimeMillis() - emailLog.getCreateTime().getTime() > 30 * 60 * 1000L){
            return ActionResult.failureParamter("激活码已失效");
        }

        MapperCriteriaBuilder userBuilder = MapperCriteriaBuilder.instances(User.class);
        userBuilder.addEq("email",token.split("@")[0]);
        List<User> users = userDAO.selectByExample(userBuilder.getExample());
        if (users.size() == 0){
            return ActionResult.failureParamter("激活码错误");
        }
        User user = users.get(0);
        if (user.getStatus() > 1){
            return ActionResult.failureParamter("您的账号已经激活成功,请登录");
        }
        user.setStatus(2);
        userDAO.updateByPrimaryKey(user);
        return ActionResult.ok(null,"激活成功");
    }

    @Override
    @Transactional
    public ActionResult reSendActiveCode(String username, String password, String email) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(email)){
            return ActionResult.failureParamter("参数不能为空");
        }
        ActionResult actionResult = loginUser(username, password);
        if (ActionResult.LOGIN_ERROR_CODE.equals(actionResult.getCode())){
            return actionResult;
        }
        MapperCriteriaBuilder userBuilder = MapperCriteriaBuilder.instances(User.class);
        userBuilder.addEq("username",username);
        userBuilder.addEq("password",DigestUtils.md5DigestAsHex(password.getBytes()));
        List<User> users = userDAO.selectByExample(userBuilder.getExample());
        User user = users.get(0);
        if (!Objects.equals(user.getEmail(),email)){
            return ActionResult.failureParamter("您的邮箱输入有误");
        }
        if (ActionResult.SUCCESS_CODE.equals(actionResult.getCode())){
            return ActionResult.failureParamter("您已经激活成功,快去登录吧");
        }
        String token = user.getEmail()+"@"+UUID.randomUUID().toString().substring(0,31-user.getEmail().length());
        user.setToken(token);
        emailUtil.sendRegisterEmail(user.getEmail(),Base64Utils.encodeToString(token.getBytes()));

        userDAO.updateByPrimaryKeySelective(user);
        EmailLog log = new EmailLog();
        log.setToUser(user.getEmail());
        log.setCreateTime(new Date());
        log.setEmailContent(token);
        emailLogDAO.insertSelective(log);

        return ActionResult.ok(null,"发送激活码成功");
    }
}
