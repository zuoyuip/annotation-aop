package org.zuoyu.examples.exception;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 下午2:37
 * @description 自定义异常.
 */
public class GoldException extends RuntimeException{

	public GoldException(String message) {
		super(message);
	}

	public GoldException(String message, Throwable cause) {
		super(message, cause);
	}


}
