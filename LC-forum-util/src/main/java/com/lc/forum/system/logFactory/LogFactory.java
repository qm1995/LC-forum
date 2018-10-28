package com.lc.forum.system.logFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiumin
 * @create 2018/10/28 21:24
 * @desc
 **/
public class LogFactory {

    private static final Logger BUSINESS = LoggerFactory.getLogger("BUSINESS");

    private static final Logger ERROR = LoggerFactory.getLogger("ERROR");

    private static final Logger DEBUG = LoggerFactory.getLogger("DEBUG");


    public static Logger getBusinessLog() {
        return BUSINESS;
    }

    public static Logger getErrorLog() {
        return ERROR;
    }

    public static Logger getDebugLog() {
        return DEBUG;
    }


}
