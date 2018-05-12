package com.onethird.insight.core.common.handler;

import com.onethird.insight.core.common.entity.ResultBean;

/**
 * 正常请求结果处理
 * @author GGY
 *
 */
public abstract class NormalResponseHandler {
	public <T> ResultBean<T> response(T response){
		return response(response, -1);
	}
	
	
	public <T> ResultBean<T> response(T response, int size){
		ResultBean<T> resultBean = new ResultBean<T>();
		resultBean.setCode(200);
		resultBean.setSize(size);
		resultBean.setResponse(response);
		return resultBean;
	}
}
