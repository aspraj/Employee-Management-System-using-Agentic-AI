package com.ems.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Logs method execution in service layer
     */
    @Pointcut("execution(* com.ems.service..*(..))")
    public void servicePointcut() {
    }

    @Before("servicePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("🔵 [ENTERING] {}.{} | Args: {}", className, methodName, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "servicePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("✅ [SUCCESS] {}.{} | Result: {}", className, methodName, result);
    }

    @AfterThrowing(pointcut = "servicePointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.error("❌ [ERROR] {}.{} | Exception: {}", className, methodName, exception.getMessage(), exception);
    }

    /**
     * Logs method execution in controller layer
     */
    @Pointcut("execution(* com.ems.controller..*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void logControllerBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("📨 [API REQUEST] {}.{}", className, methodName);
    }

    @AfterReturning(pointcut = "controllerPointcut()", returning = "result")
    public void logControllerAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("📤 [API RESPONSE] {}.{} | Status: SUCCESS", className, methodName);
    }

    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "exception")
    public void logControllerAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.error("⚠️  [API ERROR] {}.{} | Error: {}", className, methodName, exception.getMessage(), exception);
    }
}
