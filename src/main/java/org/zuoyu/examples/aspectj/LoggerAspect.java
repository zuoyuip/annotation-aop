package org.zuoyu.examples.aspectj;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author zuoyu
 * @date 2020/7/7
 * @time 下午5:55
 * @description 对Logger的切面处理.
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggerAspect {

  private final Logger loggerAspect;

  public LoggerAspect() {
    this.loggerAspect = LoggerFactory.getLogger("loggerAspect");
  }

  /** 织入点 */
  @Pointcut("@annotation(org.zuoyu.examples.aspectj.lang.annotation.Logger)")
  void logPointCut() {}

  /** 处理完请求后执行 */
  @AfterReturning(pointcut = "logPointCut()", returning = "object")
  void doAfterReturning(JoinPoint joinPoint, Object object) {
    System.out.println("joinPoint\t" + joinPoint);
    System.out.println("object\t" + object);
    // 设置方法名称
    String className = joinPoint.getTarget().getClass().getName();
    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getName();
    System.out.println("className\t" + className);
    System.out.println("methodName\t" + methodName);
    System.out.println(Arrays.toString(args));
  }

  /** 拦截异常操作 */
  @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
  void doAfterThrowing(JoinPoint joinPoint, Exception e) {}
}
