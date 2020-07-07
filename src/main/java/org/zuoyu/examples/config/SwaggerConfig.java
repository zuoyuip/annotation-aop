package org.zuoyu.examples.config;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zuoyu
 * @date 2020/6/29
 * @time 下午7:11
 * @description SwaggerAPI配置.
 */
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger.config")
public class SwaggerConfig {

  private final TypeResolver typeResolver;

  private boolean enabled = false;
  private boolean urlTemplating = true;
  private boolean forCodeGen = true;

  public SwaggerConfig(TypeResolver typeResolver) {
    this.typeResolver = typeResolver;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setUrlTemplating(boolean urlTemplating) {
    this.urlTemplating = urlTemplating;
  }

  public void setForCodeGen(boolean forCodeGen) {
    this.forCodeGen = forCodeGen;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("测试AOP")
        .description("接口说明")
        .contact(new Contact("aop测试", "https://www.zuoyu.top", "zuoyuip@foxmail.com"))
        .version("1.0.0")
        .build();
  }

  @Bean
  public Docket restApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .directModelSubstitute(LocalDate.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class)
        .alternateTypeRules(
            newRule(
                typeResolver.resolve(
                    DeferredResult.class,
                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                typeResolver.resolve(WildcardType.class)))
        .useDefaultResponseMessages(true)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(apiKey()))
        .securityContexts(Collections.singletonList(securityContext()))
        .pathMapping("/")
        .enable(enabled)
        .enableUrlTemplating(urlTemplating)
        .forCodeGeneration(forCodeGen);
  }

  /** UI设置 */
  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
        .deepLinking(true)
        .displayOperationId(false)
        .defaultModelsExpandDepth(1)
        .defaultModelExpandDepth(1)
        .defaultModelRendering(ModelRendering.EXAMPLE)
        .displayRequestDuration(true)
        .docExpansion(DocExpansion.NONE)
        .filter(false)
        .maxDisplayedTags(null)
        .operationsSorter(OperationsSorter.ALPHA)
        .showExtensions(true)
        .tagsSorter(TagsSorter.ALPHA)
        .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
        .validatorUrl(null)
        .build();
  }

  /** 默认的安全上引用 */
  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
  }

  /** 安全上下文 */
  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any())
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }
}
