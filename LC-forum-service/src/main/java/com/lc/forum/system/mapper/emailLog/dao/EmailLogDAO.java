package com.lc.forum.system.mapper.emailLog.dao;

import com.fc.forum.system.model.EmailLog;
import com.lc.forum.system.BaseDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author qiumin
 * @create 2018/11/24 20:30
 * @desc
 **/
@Mapper
@Repository
public interface EmailLogDAO extends BaseDAO<EmailLog> {
}
