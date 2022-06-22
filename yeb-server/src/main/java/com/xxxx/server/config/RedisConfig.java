package com.xxxx.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author liyongkang
 * @Date 2021/12/9
 **/

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String ,Object> redisTemplate(RedisConnectionFactory factory){

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        //Redis设置连接工厂
        redisTemplate.setConnectionFactory(factory);
        //String类型key序列化
        redisTemplate.setKeySerializer(redisSerializer);
        //String类型value序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        //Hash类型key序列化
        redisTemplate.setHashKeySerializer(redisSerializer);
        //Hash类型value序列化
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);

        return redisTemplate;
    }

}
