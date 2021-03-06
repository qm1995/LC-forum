package com.lc.forum.system.mapper.user.dao;

import com.fc.forum.system.model.User;
import com.lc.forum.system.BaseDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author qiumin
 * @create 2018/10/28 22:00
 * @desc
 **/
@Mapper
@Repository
public interface UserDAO extends BaseDAO<User> {

}
