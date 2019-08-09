package com.macaitech.saas.server.handler;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	//@Pointcut("execution(* com.macaitech.saas.server.controller..*.*(..))")
    public void pointCut() {
		System.out.println("-----test-------");
    }
	
    //@Before(value = "execution(String com.macaitech.saas.server.controller.HelloController.index())")
    //@Before("execution(* com.macaitech.saas.server.controller..*.*(..))")
	//@Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("Hello" + method.getName());
    }
}