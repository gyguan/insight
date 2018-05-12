package com.onethird.insight.core.common.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通过返回结果Bean
 * @author GGY
 *
 * @param <T>
 */
@Data
@ApiModel(value = "ResultBean")
public class ResultBean<T> implements Serializable{

	private static final long serialVersionUID = 7512246478141730074L;
	
	public static final int NO_LOGIN = -1;
	
	public static final int SUCCESS = 200;
	
	public static final int CHECK_FAIL = 400;
	
	public static final int FAIL = 500;
	
	public static final int NO_PERMISSION = 2;
	
	@ApiModelProperty(value = "提示信息", required = true)
	private String msg = "success";
	
	@ApiModelProperty(value = "HTTP请求相应码", required = true)
	private int code = SUCCESS;
	
	private int size;
	
	@ApiModelProperty(value = "请求成功返回结果", required = true)
	private T response;
	
}
