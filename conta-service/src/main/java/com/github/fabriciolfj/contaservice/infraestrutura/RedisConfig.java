package com.github.fabriciolfj.contaservice.infraestrutura;

import com.github.fabriciolfj.contaservice.domain.model.TransacaoDistribuida;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    public static final String CACHE_NAME = "transaction";

    private final RedisProperties properties;

    @Bean
    public RedisConnectionFactory redisConnectionFactoryStandalone() {
        final RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(properties.getHost());
        redisConf.setPort(properties.getPort());
        return new LettuceConnectionFactory(redisConf);
    }


    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .withCacheConfiguration(CACHE_NAME, getCacheConfiguration())
                .build();
    }

    @Bean
    public RedisCacheConfiguration getCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new Jackson2JsonRedisSerializer<>(TransacaoDistribuida.class)));
    }

    @Bean
    @Qualifier(CACHE_NAME)
    public Cache getCacheCustomer(final CacheManager cacheManager) {
        return cacheManager.getCache(CACHE_NAME);
    }
}
