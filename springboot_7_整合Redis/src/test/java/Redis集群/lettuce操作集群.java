package Redis集群;

import com.wen.RedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = RedisApplication.class)
public class lettuce操作集群 {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("name", "张三");
    }

    @Test
    public void get() {
        String name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
}
