package org.zuoyu.examples.controller.exception;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zuoyu.examples.exception.GlobalException;
import org.zuoyu.examples.utils.Result;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 上午11:21
 * @description 异常拦截.
 */
@RestControllerAdvice(basePackages = "org.zuoyu.examples.controller")
public class ControllerExceptionHandler {

  @ExceptionHandler(GlobalException.class)
  public Result globalException(GlobalException e) {
    if (StringUtils.isEmpty(e.getCode())) {
      return Result.fail(e.getMessage());
    }
    return Result.fail(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public Result handleException(Exception e){
    return Result.fail(e.getMessage());
  }
}
