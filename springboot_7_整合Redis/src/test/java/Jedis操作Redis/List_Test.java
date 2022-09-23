package Jedis操作Redis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

// Redis的命令是什么，Jedis的方法就是什么
public class List_Test {
    @Test
    public void delAll() {
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
    public void set() {
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 从左边插入一个或多个值
        jedis.lpush("mylist", "a", "b", "c");
    }

    @Test
    public void get() {
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 获取指定索引范围的数据（从左到右）
        List<String> list = jedis.lrange("mylist", 0, -1);
        System.out.println(list);// [c, b, a]
        for (String element : list) {
            System.out.println(element);
        }
    }


}
