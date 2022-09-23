package Jedis操作Redis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Hash_Test {
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

        // 添加、修改数据，给<key>集合中的 <field>键赋值<value>
        jedis.hset("hash1", "id", "1001");
        Map<String, String> map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "18");
        map.put("gender", "男");
        // 批量添加、修改多个数据，批量设置hash的值
        jedis.hmset("hash2", map);

    }

    @Test
    public void get() {
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 获取数据，从<key>集合<field>取出 value
        String hget = jedis.hget("hash1", "id");// 1001
        System.out.println(hget);
        // 获取所有数据，获取当前hash结构中的全部field和value
        Map<String, String> hash = jedis.hgetAll("hash2");// {name=小明, gender=男, age=18}
        System.out.println(hash);
    }

}
