package Jedis操作Redis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

// Redis的命令是什么，Jedis的方法就是什么
public class String_Test {
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

        // 添加、修改键值对
        jedis.set("name","小明");
        jedis.set("age","18");
        jedis.set("gender","男");
        // 添加、修改多个数据
        jedis.mset("name","小明","age","18","gender","男");
    }
    @Test
    public void get(){
        // 建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 设置密码
        jedis.auth("123456");

        // 获取当前库所有key
        System.out.println(jedis.keys("*"));// [name, gender, age]
        // 获取对应键值
        System.out.println(jedis.get("name")+jedis.get("age")+jedis.get("gender")); // 小明18男
        // 获取多个数据
        System.out.println(jedis.mget("name","age","gender")); // [小明, 18, 男]
    }



}
