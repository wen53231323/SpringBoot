package SpringDataRedis集成连接;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Redis的命令是什么，Jedis的方法就是什么
public class List_Test {
    //注入键值都为String对象的RedisTemplate对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void set() {
        //获取基本的 HashOperations 操作对象（操作Redis的Hash类型）
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        //在names集合头部插入zhangsan；返回插入成功后集合里的元素总数
        opsForList.leftPush("names", "zhangsan");
        //在names集合头部批量插入lisi、mazi；返回插入成功后集合里的元素总数
        opsForList.leftPushAll("names", "lisi", "mazi");
        //在names集合头部批量插入一个集合对象wangwu、wangwu；返回插入成功后集合里的元素总数
        opsForList.leftPushAll("names", Arrays.asList("wangwu", "xiehao"));
        //在names集合中的lisi元素前面插入 WeiHua 元素
        opsForList.leftPush("names", "lisi", "WeiHua");
        //在names集合上添加“mazi”元素，返回元素个数，0代表未添加（若集合names不存在则无法插入）
        opsForList.leftPushIfPresent("names", "mazi");

        // 还有尾插元素，以上面方法为主，把left换为right及是尾插法

        //案例示例命令：   LPUSH testNames xulingyue  wangyanke xiaogege
        //从testNames集合尾部弹出一个元素插入到names集合头部，返回添加的元素;;后两个为阻塞版
        opsForList.rightPopAndLeftPush("testNames", "names");
        opsForList.rightPopAndLeftPush("testNames", "names", Duration.ofSeconds(60L));
        opsForList.rightPopAndLeftPush("testNames", "names", 60L, TimeUnit.SECONDS);

        //查询集合里全部元素
        opsForList.range("names", 0L, -1L);

        opsForList.size("names");
        //修改集合names下坐标为3的元素为”aha“
        opsForList.set("names", 3L, "aha");
        //截取集合指定范围的元素
        opsForList.trim("names", 2L, 4L);

    }

    @Test
    public void get() {
        //获取基本的 HashOperations 操作对象（操作Redis的Hash类型）
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        //获取names集合里坐标为3的元素   0开始
        opsForList.index("names", 3L);
        //获取names集合里指定元素的坐标位置，头部查询
        opsForList.indexOf("names", "mazi");
        //获取names集合里指定元素的坐标位置，尾部查询
        opsForList.lastIndexOf("names", "mazi");
        //获取集合names内的元素数量
        opsForList.size("names");
        //从集合key中删除前count个值等于element的元素 ,返回删除的元素个数
        opsForList.remove("names", 2L, "aha");
    }

    public void del() {
        //获取基本的 HashOperations 操作对象（操作Redis的Hash类型）
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        //移除names集合中的头部第一个元素
        opsForList.leftPop("names");
        //移除names集合中的头部第一个元素,延迟阻塞 参考命令blpop key [key ...] timeout
        opsForList.leftPop("names", 1000L, TimeUnit.SECONDS);
        //移除names集合中的头部第一个元素,延迟阻塞 参考命令blpop key [key ...] timeout
        opsForList.leftPop("names", Duration.ofSeconds(30));
    }

}
