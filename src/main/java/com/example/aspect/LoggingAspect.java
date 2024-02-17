package com.example.aspect;

import com.example.database.DataBase;
import com.example.database.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LoggingAspect {
     DataBase dataBase;

     @Autowired
    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Pointcut("within(com.example..*)")
    public void callAtMyServicePublic() { }

    @Before("callAtMyServicePublic()")
    public void beforeCallAtMethod1(JoinPoint joinPoint) {
        dataBase.saveLogMessage("Calling the method " + joinPoint.getSignature());
        System.out.println("Calling the method " + joinPoint.getSignature());
    }

    //@Around("execution(* com.example..*(..))")
    /*public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        dataBase.saveLogMessage("Calling the method " + joinPoint.getSignature());
        System.out.println("Calling the method " + joinPoint.getSignature());
        return joinPoint.proceed();
    }*/
}