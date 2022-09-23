package Jedis连接Redis;



import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTestConnect {

    @Test
    public void test1() {
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 输入密码
        jedis.auth("123456");
        // 选择库
        jedis.select(0);
        // 测试连接
        String pong = jedis.ping();
        System.out.println("连接成功：" + pong);
        // 关闭连接
        jedis.close();
    }

}
