package com.accumulate.test.config;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *@System: 车贷金融
 *@Auther: hukaijia
 *@Description:  redis配置
 *@Modified By:
*/
public class DefaultRedisTemplate extends RedisTemplate<Object, Object> {
    public DefaultRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        setConnectionFactory(jedisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        setKeySerializer(stringRedisSerializer);
        setHashKeySerializer(stringRedisSerializer);
    }

}
