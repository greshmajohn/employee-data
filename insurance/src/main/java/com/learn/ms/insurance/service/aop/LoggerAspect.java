package com.learn.ms.insurance.service.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerAspect {
	
	@Autowired
    private ObjectMapper mapper;
	
	@Pointcut("within(com.learn.ms.insurance..*)")
	public void pointcut() {
		
	}
	
//	@Around("@annotation(com.learn.ms.insurance.service.aop.LogExecutionTime)")
	@Around("pointcut()")
	public Object logExcecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        log.info("\"{} \" executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());
        return proceed;
	}
	@Before("pointcut()")
	public void logMethod(JoinPoint joinPoint) throws JsonProcessingException {
		Map<String,Object> params=getParameter(joinPoint);
		
		log.info("\" before method {}, params : {} \" ", joinPoint.getSignature().getDeclaringType(),mapper.writeValueAsString(params));

		
	}
	@AfterReturning(pointcut = "pointcut()", returning = "entity")
	public void logMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) throws JsonProcessingException {
		
		log.info("\" after method {} \", Result {} ", joinPoint.getSignature(),mapper.writeValueAsString(entity));

	}
	private Map<String,Object> getParameter(JoinPoint joinPoint) {
		CodeSignature signature=(CodeSignature)joinPoint.getSignature();
		Map<String,Object> map=new HashMap<>();
		String[] parameterNames=signature.getParameterNames();
		for(int i=0;i<parameterNames.length;i++) {
			map.put(parameterNames[i], joinPoint.getArgs()[i]);
		}
		return map;
	}

}
