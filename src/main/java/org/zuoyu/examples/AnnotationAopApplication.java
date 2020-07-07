package org.zuoyu.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.zuoyu.examples.config.RedisCacheConfig;
import org.zuoyu.examples.config.StartConfig;
import org.zuoyu.examples.config.SwaggerConfig;
import org.zuoyu.examples.config.WebConfig;

/**
 * @author zuoyu
 */
@EnableTransactionManagement
@SpringBootApplication
@Import({RedisCacheConfig.class, WebConfig.class, SwaggerConfig.class, StartConfig.class})
public class AnnotationAopApplication {

  public static void main(String[] args) {
    SpringApplication.run(AnnotationAopApplication.class, args);
  }

}
