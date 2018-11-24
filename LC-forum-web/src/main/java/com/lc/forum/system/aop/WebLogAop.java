package com.lc.forum.system.aop;

import com.lc.forum.system.logFactory.LogFactory;
import com.lc.forum.system.util.json.JsonUtil;
import com.lc.forum.system.util.response.ActionResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author qiumin
 * @create 2018/11/24 22:45
 * @desc
 **/
@Component
@Aspect
public class WebLogAop {
    private long start;
    private long end;
    private static final Logger logger = LogFactory.getBusinessLog();


    @Pointcut("execution(* com.lc.forum.system.controller..*.*(..))")
    public void executeControllerMethod() {

    }

    //@Before("executeControllerMethod()")
    public void doExecuteBefore(JoinPoint joinPoint){
        start = System.currentTimeMillis();

    }

    //@After("executeControllerMethod()")
    public void doExecuteAfter(ProceedingJoinPoint joinPoint) {
        end = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();//请求的参数
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < args.length;i++) {
            if(i < args.length-1) {
                // 说明是一个自定义的类，转json
                if (args[i] != null && args[i].toString().contains("froum")){
                    sb.append(JsonUtil.convertBeanToStr(args[i])+",");
                }else {
                    sb.append(args[i] + ",");
                }
            }else {
                if (args[i] != null && args[i].toString().contains("froum")){
                    sb.append(JsonUtil.convertBeanToStr(args[i]));
                }else {
                    sb.append(args[i]);
                }
            }
        }

        logger.info(className+"."+methodName+"被执行，参数param=["+sb.toString()+"],执行时间为："+(end-start)+"毫秒");
    }

    @Around("executeControllerMethod()")
    public Object doExecuteAround(ProceedingJoinPoint joinPoint) throws Throwable {
        start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        doExecuteAfter(joinPoint);
        if (result instanceof ActionResult){
            ActionResult actionResult = (ActionResult) result;
            logger.info("返回状态码{},消息:{}",actionResult.getCode(),actionResult.getMessage());
            logger.info("返回数据:{}",JsonUtil.convertBeanToStr(actionResult.getData()));
        }
        logger.info("returning");
        return result;
    }
}
