package com.example.aspect;

import com.example.database.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LoggingAspect {
    final
    Logger logger;

    public LoggingAspect(Logger logger) {
        this.logger = logger;
    }

    @Before("execution(public * com.example.controller.*.*(..))")
    public void beforeCallAtMethod1(JoinPoint joinPoint) {
        logger.log("Calling the method " + joinPoint.getSignature());
        System.out.println("Calling the method " + joinPoint.getSignature());
    }

}