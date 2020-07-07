package org.zuoyu.examples.utils;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zuoyu
 * @date 2020/7/7
 * @time 下午4:54
 * @description Web.
 */
public class WebUtils {

  public static String url() {
    ServletRequestAttributes servletRequestAttributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest httpServletRequest =
        Objects.requireNonNull(servletRequestAttributes).getRequest();
    StringBuffer url = httpServletRequest.getRequestURL();
    String contextPath = httpServletRequest.getServletContext().getContextPath();
    return url.delete(url.length() - httpServletRequest.getRequestURI().length(), url.length())
        .append(contextPath)
        .toString();
  }
}
