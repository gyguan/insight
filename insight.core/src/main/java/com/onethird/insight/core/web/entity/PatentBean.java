package com.onethird.insight.core.web.entity;

import com.onethird.insight.core.common.entity.AbstractBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PatentBean")
public class PatentBean extends AbstractBean{
	
	private static final long serialVersionUID = 618560321690798182L;

	@ApiModelProperty(value = "申请号")
	private String applicationNo;
	
	@ApiModelProperty(value = "申请日")
	private String applicationDate;
	
	@ApiModelProperty(value = "发明人")
	private String inventor;
	
	@ApiModelProperty(value = "ipc")
	private String ipc;

}
