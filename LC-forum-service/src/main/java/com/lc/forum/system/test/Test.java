package com.lc.forum.system.test;

import com.fc.forum.system.model.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * @author qiumin
 * @create 2018/10/28 21:28
 * @desc
 **/
@Mapper
public interface Test extends tk.mybatis.mapper.common.Mapper<User> {


}
