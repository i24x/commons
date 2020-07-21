package com.lsl.commons.config.log;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;

/**
 * 操作日志记录处理
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * *(..)) && @annotation(com.lsl.commons.config.log.Log)")
    public void logPointCut() {}

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Log log = annotationLog(joinPoint);
        String[] logpropertys = new String[2];
        logpropertys[0] = log.title();
        logpropertys[1] = log.msg();
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                args[i] = JSON.toJSONString(args[i]);
            }
        }
        String argsStr = Joiner.on(",").skipNulls().join(args);
        LogUtil.getInstance()
            .info("\n==>请求方法:" + signature + ",描述:" + Joiner.on(" ").skipNulls().join(logpropertys) + ",参数:" + argsStr);
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log annotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
