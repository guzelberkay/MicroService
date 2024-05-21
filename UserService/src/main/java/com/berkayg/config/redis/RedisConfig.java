package com.berkayg.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
@Configuration
@EnableCaching
@EnableRedisRepositories //eğer repoları crudRepositoryden extend alarak Redis Db kullanacaksanız geçerli olur.
public class RedisConfig {
    @Value("${userservice.redis.host}")
    String redisHost;
    @Value("${userservice.redis.port}")
    int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }
}