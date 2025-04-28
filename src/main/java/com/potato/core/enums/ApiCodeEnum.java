package com.potato.core.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiCodeEnum {

	SUCCESS(200)
	;

	private final Integer num;
	public int getNumber() { return num; }

}
