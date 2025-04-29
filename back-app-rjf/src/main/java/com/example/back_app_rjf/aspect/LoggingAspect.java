package com.example.back_app_rjf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger aspectLogger = LoggerFactory.getLogger("AspectLogger");

    /** Controllerクラスのメソッドの実行前後にログを出力するポイントカット. */
    @Pointcut("execution(* com.example.back_app_rjf.controller..*(..))")
    public void controllerMethods() {
        // Pointcut for all methods in the controller package
    }

    /** Controllerクラスのメソッドの実行前にログを出力する. */
    @Before("controllerMethods()")
    public void logBeforeController(JoinPoint joinPoint) {
        aspectLogger.info("Executing method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    /** Controllerクラスのメソッドの実行後にログを出力する. */
    @After("controllerMethods()")
    public void logAfterController(JoinPoint joinPoint) {
        aspectLogger.info("Finished executing method: {}", joinPoint.getSignature());
    }

    /** Serviceクラスのメソッドの実行前後にログを出力するポイントカット. */
    @Pointcut("execution(* com.example.back_app_rjf.controller..*(..))")
    public void serviceMethods() {
        // Pointcut for all methods in the controller package
    }

    /** Serviceクラスのメソッドの実行前にログを出力する. */
    @Before("serviceMethods()")
    public void logBeforeService() {
        aspectLogger.info("Service method execution started");
    }

    /** Serviceクラスのメソッドの実行後にログを出力する. */
    @After("serviceMethods()")
    public void logAfterService() {
        aspectLogger.info("Service method execution finished");
    }

}
