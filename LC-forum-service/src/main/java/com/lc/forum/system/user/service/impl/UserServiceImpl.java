package com.lc.forum.system.user.service.impl;

import com.fc.forum.system.model.User;
import com.lc.forum.system.builder.MapperCriteriaBuilder;
import com.lc.forum.system.email.util.EmailUtil;
import com.lc.forum.system.mapper.user.dao.UserDAO;
import com.lc.forum.system.user.service.UserService;
import com.lc.forum.system.util.response.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

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

    @Override
    @Transactional
    public ActionResult userRegister(User user) {
        String md5Pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pwd);
        userDAO.insertSelective(user);
        emailUtil.sendMail(user.getEmail(),"欢迎您注册!");
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
        return ActionResult.ok(users.get(0),"登录成功");
    }
}
