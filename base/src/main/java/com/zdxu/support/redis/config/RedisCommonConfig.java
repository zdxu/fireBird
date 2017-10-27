package com.zdxu.support.redis.config;

import lombok.Data;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaodexu on 2017/9/3.
 */
@Data
public class RedisCommonConfig {

    private int port = Protocol.DEFAULT_PORT;
    private String host = Protocol.DEFAULT_HOST;
    private String password = "";
    private int database = Protocol.DEFAULT_DATABASE;
    private int timeout = 3000;

    private int maxTotal = 100;
    private int maxIdle = 80;
    private int minIdle = 10;
    private long maxWaitMillis = 1000;
    private long timeBetweenEvictionRunsMillis = TimeUnit.MINUTES.toMillis(10);
    private long minEvictableIdleTimeMillis = TimeUnit.MINUTES.toMillis(59);
    private int numTestsPerEvictionRun = -5;
    private boolean testOnBorrow = true;

    String master;
    String nodes;

    JedisConnectionFactory createJedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        poolConfig.setTestOnReturn(testOnBorrow);

        RedisSentinelConfiguration sentinelConfiguration = null;
        if(!StringUtils.isEmpty(master) && !StringUtils.isEmpty(nodes)){
            List<RedisNode> sentinels = createSentinels(nodes);
            if(sentinels != null) {
                sentinelConfiguration = new RedisSentinelConfiguration();
                sentinelConfiguration.setMaster(master);
                sentinelConfiguration.setSentinels(sentinels);
            }
        }

        JedisConnectionFactory factory;
        if(!StringUtils.isEmpty(master) && !StringUtils.isEmpty(nodes)){
            factory = new JedisConnectionFactory(sentinelConfiguration, poolConfig);
        }else {
            factory = new JedisConnectionFactory(poolConfig);
        }

        factory.setPort(port);
        factory.setHostName(host);
        factory.setDatabase(database);
        factory.setPassword(password);
        factory.setTimeout(timeout);

        return factory;
    }

    List<RedisNode> createSentinels(String nodes) {
        String[] arrNode = nodes.split(",");
        List<RedisNode> redisNodes = new ArrayList<>();
        for (int i = 0; arrNode != null && i < arrNode.length; i++) {
            String[] nodeInfo = arrNode[i].split(":");
            RedisNode redisNode = nodeInfo.length == 2 ? new RedisNode(nodeInfo[0], Integer.parseInt(nodeInfo[1])) : null;
            if (redisNode != null)
                redisNodes.add(redisNode);
        }
        return redisNodes;
    }

    RedisTemplate createRedisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    StringRedisTemplate createStringRedisTemplate(JedisConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }
}
