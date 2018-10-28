package com.lc.forum.system.controller.user.dao;

import com.fc.forum.system.model.User;
import com.lc.forum.system.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qiumin
 * @create 2018/10/28 22:00
 * @desc
 **/
@Mapper
public interface UserDAO extends BaseDAO<User> {
}
