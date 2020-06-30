package org.zuoyu.examples.utils;

import java.util.HashMap;
import org.springframework.util.Assert;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 上午11:39
 * @description Rest结果包装.
 */
public class Result extends HashMap<String, Object> {

	/** 状态码 */
	public static final String CODE_PARAM = "code";

	/** 返回内容 */
	public static final String MSG_PARAM = "msg";

	/** 数据对象 */
	public static final String DATA_PARAM = "data";

	public Result(){
		super(16);
	}

	public Result(int code, String msg){
		super(16);
		super.put(CODE_PARAM, code);
		super.put(MSG_PARAM, msg);
	}

	public Result(int code, Object data){
		super(16);
		super.put(CODE_PARAM, code);
		Assert.notNull(data, "data can't is null!");
		super.put(DATA_PARAM,data);
	}

	public Result(int code, String msg, Object data){
		super(16);
		super.put(CODE_PARAM, code);
		super.put(MSG_PARAM, msg);
		Assert.notNull(data, "data can't is null!");
		super.put(DATA_PARAM,data);
	}




}
