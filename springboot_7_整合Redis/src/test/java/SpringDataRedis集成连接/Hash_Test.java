package SpringDataRedis集成连接;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.Jedis;

import java.util.*;

public class Hash_Test {

    //注入键值都为String对象的RedisTemplate对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void set() {
        //获取基本的 HashOperations 操作对象（操作Redis的Hash类型）
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        //往Student的Hash表中插入一个name为“zhangsan”
        opsForHash.put("Student", "name", "zhangsan");
        //批量往Student的Hash表中插入age、salary
        Map<String, String> map = new HashMap<>();
        map.put("age", "22");
        map.put("salary", "3000.88");
        opsForHash.putAll("Student", map);
        //往Student的Hash表中插入name为list的值，注：若name的key存在则无法插入，也无法更新，用于插入不存在的key
         opsForHash.putIfAbsent("Student", "name", "lisi");
        //为Hash中某个key(整数、小数)累加指定的值；为负数就相减
         opsForHash.increment("Student", "age", -30L);
         opsForHash.increment("Student", "salary", -5000D);

        //迭代，具体参考hcsan
        //测试数据：hmset testHash k1 v1 k2 v2 k3 v3 k4 v4 k5 v5 k6 v6 k7 v7
        ScanOptions.scanOptions()
                .count(1)
                .match("k*") //过滤符合指定的key   *代表以 k 开头的键
                .build();
        //ScanOptions.NONE迭代全部
        Cursor<Map.Entry<Object, Object>> kv = opsForHash.scan("testHash", ScanOptions.NONE);
        System.out.println(kv.getCursorId());//获取光标ID
        System.out.println(kv.getPosition());//获取当前迭代的位置
        System.out.println(kv.isClosed());   //是否已关闭
        while (kv.hasNext()) {
            Map.Entry<Object, Object> next = kv.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }
    }

    @Test
    public void get() {
        //获取基本的 HashOperations 操作对象（操作Redis的Hash类型）
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();

        //获取Student的Hash中key为name的值
        Object name = opsForHash.get("Student", "name");
        //获取当前Student的HAsh表中的key数量
        Long stuSize = opsForHash.size("Student");
        //获取Student的Hash表中的全部key和value
        Set<Object> stuKeys = opsForHash.keys("Student");
        List<Object> stuValue = opsForHash.values("Student");
        //获取Student的Hash中key为name的字符长度
        Long aLong = opsForHash.lengthOfValue("Student", "name");
        //获取Student的Hash中是否存在key为name的数据
        Boolean aBoolean1 = opsForHash.hasKey("Student", "name");
        //批量获取Hash表中指定多个key的值
        opsForHash.multiGet("Student", Arrays.asList("name", "age"));
        //获取基本操作key对象
        RedisOperations<String, ?> operations = opsForHash.getOperations();
    }

    public void del(){
        //获取基本的 HashOperations 操作对象（操作Redis的Hash类型）
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();

        //删除Hash表中指定key
        Long delName = opsForHash.delete("Student", "name");

    }

}
