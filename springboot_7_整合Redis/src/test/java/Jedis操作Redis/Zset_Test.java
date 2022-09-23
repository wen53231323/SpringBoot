package Jedis操作Redis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class Zset_Test {
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
        jedis.zadd("zset",5,"五");
        jedis.zadd("zset",1,"一");
        jedis.zadd("zset",3,"三");
        jedis.zadd("zset",4,"四");
        jedis.zadd("zset",2,"二");
    }
    @Test
    public void get(){
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 返回有序集 key 中，下标在<start><stop>之间的元素（默认根据score从小到大排序）
        System.out.println(jedis.zrange("zset", 0, -1)); // [一, 二, 三, 四, 五]
    }
}
