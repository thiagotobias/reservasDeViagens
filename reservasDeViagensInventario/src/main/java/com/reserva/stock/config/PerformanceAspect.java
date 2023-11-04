package com.reserva.stock.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void repositoryClassMethods() {
    }

    @SneakyThrows
    @Around("repositoryClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.nanoTime();
        Object retVal = proceedingJoinPoint.proceed();
        long endTime = System.nanoTime();

        String methodName = proceedingJoinPoint.getSignature().getName();

        log.info("Execution of {} took {}ms",
                methodName,
                TimeUnit.NANOSECONDS
                        .toMillis(endTime - startTime)
        );
        return retVal;
    }
}
