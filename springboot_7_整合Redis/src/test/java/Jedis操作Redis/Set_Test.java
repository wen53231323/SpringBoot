package Jedis操作Redis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class Set_Test {
    @Test
    public void delAll(){
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        //数据清除，清空当前库
        jedis.flushDB();
        //数据清除，通杀全部库
        jedis.flushAll();
    }

    @Test
    public void set(){
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 添加数据
        jedis.sadd("set","爬山");
        jedis.sadd("set","游泳");
        jedis.sadd("set","健身");
    }
    @Test
    public void get(){
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 删除一个或多个数据，不存在的成员元素会被忽略
        jedis.srem("set", "健身");
        // 获取数据（获取全部数据）
        System.out.println(jedis.smembers("set"));// [游泳, 爬山]

    }
}
