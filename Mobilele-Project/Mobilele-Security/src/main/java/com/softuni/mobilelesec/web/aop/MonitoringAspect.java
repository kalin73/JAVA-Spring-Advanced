package com.softuni.mobilelesec.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Component
@Aspect
public class MonitoringAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    @Around("Pointcuts.warnIfExecutionTimeExceeds()")
    Object monitorExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        WarnIfExecutionExceeds annotation = getAnnotation(pjp);
        long threshold = annotation.threshold();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        var result = pjp.proceed();
        stopWatch.stop();

        long executionTime = stopWatch.lastTaskInfo().getTimeMillis();

        if (executionTime > threshold) {
            LOGGER.warn("The method {} executed in {} millis which is more then the acceptable threshold of {} millis.",
                    pjp.getSignature(), executionTime, annotation.threshold());
        }

        return result;
    }

    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint pjp) {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        return method.getAnnotation(WarnIfExecutionExceeds.class);
    }
}
