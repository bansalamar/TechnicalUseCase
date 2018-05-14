package com.example.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	private final Logger logger = LoggerFactory.getLogger("applog");

	@Around("execution(* com.example.controller.*.*(..)) ||execution(* com.example.exception.*.*(..))")
	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		StringBuffer logMessage = new StringBuffer();
		logMessage.append(className);
		logMessage.append(".");
		logMessage.append(methodName);
		logMessage.append("(");
		// append args
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logMessage.append(args[i]).append(",");
		}
		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}
		logMessage.append(") req ");
		logger.info(logMessage.toString());

		Object retVal = joinPoint.proceed();

		StringBuffer logMessageExit = new StringBuffer();
		logMessageExit.append(className);
		logMessageExit.append(".");
		logMessageExit.append(methodName);
		logMessageExit.append("(");
		logMessageExit.append(") res " + retVal);

		logger.info(logMessageExit.toString());

		return retVal;
	}
}