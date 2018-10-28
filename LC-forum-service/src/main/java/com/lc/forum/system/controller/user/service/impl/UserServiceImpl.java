package com.lc.forum.system.controller.user.service.impl;

import com.fc.forum.system.model.User;
import com.lc.forum.system.controller.user.dao.UserDAO;
import com.lc.forum.system.controller.user.service.UserService;
import com.lc.forum.system.util.response.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qiumin
 * @create 2018/10/28 22:23
 * @desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public ActionResult userRegister(User user) {

        userDAO.insertSelective(user);
        return ActionResult.ok(null);
    }

    @Override
    public ActionResult listAllUser() {
        return null;
    }

    @Override
    public ActionResult findUser(Integer userId) {
        return null;
    }

    @Override
    public ActionResult loginUser(String username, String password) {
        return null;
    }
}
