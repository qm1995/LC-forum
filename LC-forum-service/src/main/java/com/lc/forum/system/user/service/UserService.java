package com.lc.forum.system.user.service;

import com.fc.forum.system.model.User;
import com.lc.forum.system.util.response.ActionResult;

/** 管理用户service层
 * @author qiumin
 * @create 2018/10/28 22:02
 * @desc
 **/
public interface UserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    ActionResult userRegister(User user);

    /**
     * 列出所有用户数据
     * @return
     */
    ActionResult listAllUser();

    /**
     * 根据id查询用户数据
     * @param userId
     * @return
     */
    ActionResult findUser(Integer userId);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ActionResult loginUser(String username,String password);

    /**
     * 用户激活
     * @param activeCode
     * @return
     */
    ActionResult activatingUser(String activeCode);

    /**
     * 重新发送激活码
     * @param username
     * @param password
     * @param email
     * @return
     */
    ActionResult reSendActiveCode(String username, String password, String email);

    /**
     * 获取用户名和临时密码
     * @param email
     * @return
     */
    ActionResult getUsernameAndTempPass(String email);

    /**
     * 重置密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    ActionResult resetPassword(String username, String password,String newPassword);
}
