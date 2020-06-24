package org.zuoyu.examples.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zuoyu
 * @date 2020/6/24
 * @time 下午2:16
 * @description Redis缓存配置.
 */
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
  /** 自定义主键生成策略 */
  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(target.getClass().getName());
      stringBuilder.append(".");
      stringBuilder.append(method.getName());
      for (Object object : params) {
        stringBuilder.append(object.toString());
      }
      return stringBuilder.toString();
    };
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new ParameterNamesModule())
        .registerModule(new JavaTimeModule());
    return objectMapper;
  }

  @Bean
  public GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer(ObjectMapper objectMapper) {
    return new GenericJackson2JsonRedisSerializer(objectMapper);
  }

  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    return StringRedisSerializer.UTF_8;
  }

  /** Redis序列化配置 */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      LettuceConnectionFactory lettuceConnectionFactory,
      GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer,
      StringRedisSerializer stringRedisSerializer) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(lettuceConnectionFactory);
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setEnableDefaultSerializer(true);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  /** 缓存管理 */
  @Bean
  public CacheManager cacheManager(
      LettuceConnectionFactory lettuceConnectionFactory,
      GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer,
      StringRedisSerializer stringRedisSerializer) {

    RedisCacheConfiguration redisCacheConfiguration =
        RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    jackson2JsonRedisSerializer));
    return RedisCacheManager.builder(
            RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory))
        .cacheDefaults(redisCacheConfiguration)
        .build();
  }
}
