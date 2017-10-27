package com.zdxu.support.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
@ConditionalOnExpression("${redis.enable:true}")
public class RedisConfig extends RedisCommonConfig {

    @Bean
    @Override
    JedisConnectionFactory createJedisConnectionFactory() {
        return super.createJedisConnectionFactory();
    }

    @Bean
    @Override
    RedisTemplate createRedisTemplate(JedisConnectionFactory connectionFactory) {
        return super.createRedisTemplate(connectionFactory);
    }

    @Bean
    @Override
    StringRedisTemplate createStringRedisTemplate(JedisConnectionFactory connectionFactory) {
        return super.createStringRedisTemplate(connectionFactory);
    }
}
