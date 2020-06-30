package org.zuoyu.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.zuoyu.examples.config.RedisCacheConfig;
import org.zuoyu.examples.config.SwaggerConfig;
import org.zuoyu.examples.config.WebConfig;

/**
 * @author zuoyu
 */
@SpringBootApplication
@Import({RedisCacheConfig.class, WebConfig.class, SwaggerConfig.class})
public class AnnotationAopApplication {

  public static void main(String[] args) {
    SpringApplication.run(AnnotationAopApplication.class, args);
  }

}
