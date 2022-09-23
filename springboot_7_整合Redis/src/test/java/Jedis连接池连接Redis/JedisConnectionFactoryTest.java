package Jedis连接池连接Redis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;


public class JedisConnectionFactoryTest {

    @Test
    public void testString() {
        // 通过Jedis连接池建立连接
        Jedis jedis = JedisConnectionFactory.getJedis();
        // 选择库
        jedis.select(0);
        // 测试连接
        String pong = jedis.ping();
        System.out.println("连接成功：" + pong);
        // 添加、修改键值对
        String set = jedis.set("name", "小明");
        // 获取对应键值
        System.out.println(jedis.get("name"));
        // 关闭连接
        jedis.close();
    }

}

