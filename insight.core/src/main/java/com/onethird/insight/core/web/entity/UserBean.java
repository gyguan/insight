package com.onethird.insight.core.web.entity;

import com.onethird.insight.core.common.entity.AbstractBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserBean")
public class UserBean extends AbstractBean{
	
	private static final long serialVersionUID = 7102350663788104295L;
	
	@ApiModelProperty(value = "用户名")
	private String name;
	
	@ApiModelProperty(value = "phone")
	private String phone;
	
	@ApiModelProperty(value = "email")
	private String email;
}
