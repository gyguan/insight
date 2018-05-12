package com.onethird.insight.core.common.aop;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.onethird.insight.core.common.entity.ResultBean;


/**
 * 统一处理controller跑出的异常
 * @author GGY
 *
 */
@Aspect
@Component
@Order(1)
public class ControllerExceptionAop {
	private static Logger logger  = Logger.getLogger(ControllerExceptionAop.class);
	
	@Pointcut("execution(public com.onethird.insight.core.common.ResultBean ((com.onethird.insight..*) && (@org.springframework.web.bind.annotation.RestController *)).*(..)) ")
	public void response() {
	}
	
	@SuppressWarnings("unchecked")
	public <T> ResultBean<T> doAround(ProceedingJoinPoint joinPoint){
		try {
			ResultBean<T> response = (ResultBean<T>) joinPoint.proceed();
			return response;
		} catch (ConstraintViolationException e) {
			throw e;
		} catch (Throwable e) {
			return handlerException(e);
		}
	}
	
	private <T> ResultBean<T> handlerException(Throwable e){
		ResultBean<T> resultBean = new ResultBean<T>();
		resultBean.setMsg(e.getMessage());
		resultBean.setCode(500);
		logger.error(e.getMessage(), e);
		return resultBean;
	}
}
