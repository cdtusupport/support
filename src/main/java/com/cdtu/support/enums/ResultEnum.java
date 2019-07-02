package com.cdtu.support.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

	Test(0, "Test");

	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
