package org.zuoyu.examples.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zuoyu
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {

	/**
	 * 成功
	 */
	SUCCESS(200, "成功"),

	/**
	 * 失败
	 */
	FAIL(500, "异常"),
;
	private final Integer code;
	private final String message;


}
