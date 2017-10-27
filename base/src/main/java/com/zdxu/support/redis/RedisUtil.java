package com.zdxu.support.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * getString:(获取值). <br/>
     * @author zdxu
     * @param key key
     * @return redis 存放的值
     */
    public String getString(String key){
        return (String) getValue(key);
    }

    /**
     * getValue:(获取值). <br/>
     * @author zdxu
     * @param key key
     * @return redis 存放的值
     */
    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * setValue:(添加值). <br/>
     * @author zdxu
     * @param key key
     * @param value key
     */
    public void setValue(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * setValue:(添加值). <br/>
     * @author zdxu
     * @param key key
     * @param value value
     * @param timeout 过期时间（以秒为单位）
     */
    public void setValue(String key, Object value, long timeout){
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * setValue:(添加值). <br/>
     * @author zdxu
     * @param key key
     * @param value value
     * @param timeout 过期时间
     * @param unit 时间单位
     */
    public void setValue(String key, Object value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * del:(删除). <br/>
     * @author zdxu
     * @param key key
     */
    public void del(String key){
        redisTemplate.delete(key);
    }

    /**
     * incr:(键值自增长，默认+1). <br/>
     * @author zdxu
     * @param key key
     * @return value
     */
    public Long incr(String key){
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * incr:(键值自增长). <br/>
     * @author zdxu
     * @param key key
     * @param delta delta
     * @return value
     */
    public Long incr(String key, long delta){
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * hasKey:(是否还有指定键值). <br/>
     * @author zdxu
     * @param key key
     * @return 是否还有指定键值
     */
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * getExpire:(获取键值过期时间). <br/>
     * @author zdxu
     * @param key key
     * @return 键值的过期时间
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * keys:(正则获取键值Set). <br/>
     * @author zdxu
     * @param pattern 正则表达式
     * @return 键值的Set
     */
    public Set<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }
}
