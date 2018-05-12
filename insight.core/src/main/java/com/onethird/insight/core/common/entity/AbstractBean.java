package com.onethird.insight.core.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AbstractEntity")
public abstract class AbstractBean implements Serializable{

	private static final long serialVersionUID = -3012563066510181895L;
	
	@ApiModelProperty(value = "id")
	public Long id;
	
	@ApiModelProperty(value = "创建人")
	private Long createdBy;
	
	@ApiModelProperty(value = "创建时间")
	private Date createdDate;
	
	@ApiModelProperty(value = "更新人")
	private Long lastUpdatedBy;
	
	@ApiModelProperty(value = "更新时间")
	private Date lastUpdatedDate;
	
	private Map<String, Object> extendData;
	
	public void addExtendData(String key, Object value) {
		if(extendData == null) {
			extendData = new HashMap<>();
		}
		extendData.put(key, value);
	}
	
}
