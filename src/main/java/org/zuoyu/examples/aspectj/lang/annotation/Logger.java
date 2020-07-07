package org.zuoyu.examples.aspectj.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.zuoyu.examples.aspectj.lang.enums.BusinessType;

/**
 * @author zuoyu
 * @date 2020/7/7
 * @time 下午5:36
 * @description 自定义日志注解.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {

  BusinessType businessType() default BusinessType.OTHER;
}
