package com.cdtu.support.exception;

import com.cdtu.support.enums.ResultEnum;

public class SupportException extends RuntimeException {
	private Integer code;

	public SupportException(ResultEnum resultEnum){
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
}
