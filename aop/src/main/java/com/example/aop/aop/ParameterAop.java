package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        System.out.println("<Paramter Aop before>");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
//        System.out.println(method.getName());

        Object[ ] args = joinPoint.getArgs();
        for (Object obj: args){
            System.out.println("type:  "+obj.getClass().getSimpleName());
            System.out.println("value: "+obj);
        }
        System.out.println("<Parameter Aop before 끝>");
    }

    @AfterReturning(value = "cut()", returning = "returnObject")
    public void afterReturn(JoinPoint joinPoint, Object returnObject){
        System.out.println("<Paramter Aop after>");
        System.out.println("after return object");
        System.out.println(returnObject);
        System.out.println("<Parameter Aop after 끝>");
    }
}
