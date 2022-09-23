package Jedis连接池连接Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;
    //1. 创建连接池配置信息
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8); //最多允许创建多少个连接
        poolConfig.setMaxIdle(0);  //最大空闲连接
        poolConfig.setMinIdle(0);  // 最小空闲数
        poolConfig.setMaxWaitMillis(1000); // 当连接池空了之后,多久没获取到Jedis对象,就超时

        //2. 创建连接池
        jedisPool = new JedisPool(poolConfig,
                "192.168.10.100",
                6379,
                1000,
                "123456"
        );

    }

    public static Jedis getJedis() {
        //3. 通过连接池获取jedis对象
        return jedisPool.getResource();
    }
}
