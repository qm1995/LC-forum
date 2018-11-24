package com.lc.forum.system.util.json;

import com.alibaba.fastjson.JSON;

/**
 * @author qiumin
 * @create 2018/11/24 22:48
 * @desc
 **/
public class JsonUtil {


    public static String convertBeanToStr(Object bean){
        return JSON.toJSONString(bean);
    }

    public static <T> T convertStrToBean(Class<T> clazz,String str){
        return JSON.parseObject(str,clazz);
    }
}
