package org.zuoyu.examples.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zuoyu
 * @date 2020/6/29
 * @time 下午7:03
 * @description Web配置.
 */
public class WebConfig  extends WebMvcConfigurationSupport {

	/**
	 * 时区配置
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
	{
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}

	/**
	 * 跨域
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders(CorsConfiguration.ALL)
				.allowedMethods(CorsConfiguration.ALL)
				.allowedOrigins(CorsConfiguration.ALL)
				.allowCredentials(true)
				.maxAge(3600L);
		super.addCorsMappings(registry);
	}

	/**
	 * 放行swagger
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 解决乱码
	 */
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		super.configureMessageConverters(converters);
	}

}
