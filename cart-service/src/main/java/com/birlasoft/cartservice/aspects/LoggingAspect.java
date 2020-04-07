package com.birlasoft.cartservice.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    public static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.birlasoft.cartservice..*(..)))")
    public Object logAroundAMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = proceedingJoinPoint.getArgs();
        String methodName = proceedingJoinPoint.getSignature().getName();
        if (args.length == 1) {
            LOG.info("Executing " + className + "." + methodName + "{" + args[0].toString() + "}");
        } else {
            StringBuilder builder = new StringBuilder();
            Arrays.asList(args).forEach($ -> builder.append($.toString() + ","));
            LOG.info("Executing " + className + "." + methodName + "{ " + builder.toString() + " }");
        }
        final Object returned = proceedingJoinPoint.proceed();
        LOG.info(className + "." + methodName + "=>{ " + returned + " }");
        return returned;
    }
}
