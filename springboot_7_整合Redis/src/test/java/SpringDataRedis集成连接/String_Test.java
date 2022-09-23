package SpringDataRedis集成连接;


import com.wen.RedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = RedisApplication.class)
public class String_Test {

    // 注入键值都为String对象的RedisTemplate对象
    // 因为操作的K-V都是String类型，所以使用系统自带的StringRedisTemplate模板对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void set() {
        //获取基本的 ValueOperations 操作对象（操作Redis的String类型）
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();

        // 设置当前的key以及value值
        opsForValue.set("key1", "Redis1");
        // 设置一个普通的键值，存在偏移的设置
        opsForValue.set("key2", "Redis2", 6);
        // 设置一个普通键值，并设置过期时间60秒
        opsForValue.set("key3", "Redis3", Duration.ofSeconds(6000));
        // 在key为“message”的值后面追加 " Good" 最终的key3为"Redis3 Good"
        opsForValue.append("key3", " Good");

        // 将key中储存的数字值增加 1 或者后面的增加指定值
        opsForValue.set("key4", "20");
        opsForValue.increment("key4");// 增加 1
        // opsForValue.increment("key4", 2L);// 增加 2
        // opsForValue.increment("key4", 3.4D);// 增加3.4
        // 将key中储存的数字值减 1 或减去指定值
        opsForValue.decrement("key4");// 减少 1
        opsForValue.decrement("key4", 2L);// 减少 2

        // setIfAbsent只能新增操作，setIfPresent只能更新操作
        opsForValue.setIfAbsent("key5", "Redis5");
        opsForValue.setIfPresent("key5", "Redis6");
        // 批量设置String的键值
        Map<String, String> maps = new HashMap<>();
        maps.put("keyA", "valueA");
        maps.put("keyB", "valueB");
        maps.put("keyC", "valueC");
        opsForValue.multiSet(maps);

        //其它公共方法操作，就和RedisTemplate下的公共方法差不多
        RedisOperations<String, String> operations = opsForValue.getOperations();
    }

    @Test
    public void get() {
        //获取基本的 ValueOperations 操作对象（操作Redis的String类型）
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();

        // 获取指定key所储存的字符串值的长度
        System.out.println( opsForValue.size("key1"));
        // 获取对应键值，根据 key 查询对应的 value ，如果不存在，返回空（nil）
        System.out.println(opsForValue.get("key2"));
        // 获取key为“key2”的值，并指定偏移，打印为 "Red"
        System.out.println(opsForValue.get("key2", 2, 8));
        // 先获取key4值，并把key4值更改为100
        System.out.println(opsForValue.getAndSet("key4", "100"));
        // 批量获取值
        System.out.println(opsForValue.multiGet(Arrays.asList("keyA", "keyB", "keyC")));
    }

    @Test
    public void del() {
        //在Redis数据库中删除指定的键
        Boolean studentDelBoo = stringRedisTemplate.delete("name");
    }

    @Test
    public void delAll() {

    }

}
