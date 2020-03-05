package com.xliu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/5 19:16
 */

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.xliu.web.*.*(..))")
    public void log(){};

    @Around("log()")
    public Object doAround(ProceedingJoinPoint jp) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String ip = request.getRemoteAddr();
            String classMethod = jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName();
            Object[] args = jp.getArgs();
            RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
            logger.info("Request:{}",requestLog);
            Object proceed = jp.proceed();
            logger.info("result:{}",proceed);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw  new RuntimeException(throwable);
        }
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }


}
