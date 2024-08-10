package com.softuni.mobilelesec.web.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("@annotation(WarnIfExecutionExceeds)")
    void warnIfExecutionTimeExceeds() {
    }
}
