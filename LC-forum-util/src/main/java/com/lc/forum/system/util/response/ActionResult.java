package com.lc.forum.system.util.response;

import java.util.Objects;

/**
 * @author qiumin
 * @create 2018/10/28 22:03
 * @desc
 **/
public class ActionResult {

    // 成功
    private static final Integer SUCCESS_CODE = 200;

    // 参数错误
    private static final Integer PARAMTER_ERROR_CODE = 201;

    // 服务器错误
    private static final Integer SERVER_ERROR_CODE = 500;

    // 非法请求
    private static final Integer ILLEGAL_ERROR_CODE = 202;

    // 用户未登录
    private static final Integer LOGIN_ERROR_CODE = 204;


    private Integer code;

    private Boolean isSuccess;

    private Object data;

    private String message;

    private ActionResult(Integer code,Boolean isSuccess,Object data,String message){
        this.code = code;
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    public static ActionResult ok(Object data,String message){
        return new ActionResult(SUCCESS_CODE,true,data,message);
    }

    public static ActionResult ok(Object data){
        return ok(data,"success");
    }

    public static ActionResult build(Integer code,Boolean isSuccess,Object data,String message){
        return new ActionResult(code, isSuccess, data, message);
    }

    public static ActionResult failureServer(String message){
        return build(SERVER_ERROR_CODE,false,null,message);
    }

    public static ActionResult failureParamter(String message){
        return build(PARAMTER_ERROR_CODE,false,null,message);
    }

    public static ActionResult failureLogin(String message){
        return build(LOGIN_ERROR_CODE,false,null,message);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
