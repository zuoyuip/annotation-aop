package org.zuoyu.examples.exception;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.zuoyu.examples.enums.ResultEnum;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 下午2:37
 * @description 自定义异常.
 */
@Getter
public class GlobalException extends RuntimeException{

	private final String message;
	private Integer code;

	public GlobalException(String message) {
		super(message);
		this.message = message;
	}

	public GlobalException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public GlobalException(String message, Integer code){
		this.message = message;
		this.code = code;
	}

	public GlobalException(@NonNull ResultEnum resultEnum){
		this.message = resultEnum.getMessage();
		this.code = resultEnum.getCode();
	}

}
