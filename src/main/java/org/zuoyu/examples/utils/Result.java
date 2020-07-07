package org.zuoyu.examples.utils;

import java.util.HashMap;
import org.springframework.util.Assert;
import org.zuoyu.examples.enums.ResultEnum;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 上午11:39
 * @description Rest结果包装.
 */
public class Result extends HashMap<String, Object> {

  /** 状态码 */
  private static final String CODE_PARAM = "code";

  /** 返回内容 */
  private static final String MSG_PARAM = "msg";

  /** 数据对象 */
  private static final String DATA_PARAM = "data";

  private Result() {
    super(16);
  }

  private Result(int code, String msg) {
    super(16);
    super.put(CODE_PARAM, code);
    super.put(MSG_PARAM, msg);
  }

  private Result(int code, Object data) {
    super(16);
    super.put(CODE_PARAM, code);
    Assert.notNull(data, "data can't is null!");
    super.put(DATA_PARAM, data);
  }

  private Result(int code, String msg, Object data) {
    super(16);
    super.put(CODE_PARAM, code);
    super.put(MSG_PARAM, msg);
    Assert.notNull(data, "data can't is null!");
    super.put(DATA_PARAM, data);
  }

  /** 成功状态 */
  public static Result success() {
    return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
  }

  /** 成功状态 */
  public static Result success(Object data) {
    return new Result(ResultEnum.SUCCESS.getCode(), data);
  }

  /** 失败状态 */
  public static Result fail() {
    return new Result(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage());
  }

  /** 失败状态 */
  public static Result fail(String message) {
    return fail(ResultEnum.FAIL.getCode(), message);
  }

  /** 失败状态 */
  public static Result fail(Integer code, String message) {
    return new Result(code, message);
  }

  /** 自定义状态 */
  public static Result custom(ResultEnum resultEnum) {
    return new Result(resultEnum.getCode(), resultEnum.getMessage());
  }

  /** 自定义状态 */
  public static Result custom(ResultEnum resultEnum, Object data) {
    return new Result(resultEnum.getCode(), resultEnum.getMessage(), data);
  }

  /** 自定义状态 */
  public static Result custom(int code, Object data) {
    return new Result(code, data);
  }
}
