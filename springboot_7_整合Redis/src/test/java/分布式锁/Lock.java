package 分布式锁;

import com.wen.RedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = RedisApplication.class)
public class Lock {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testLock() {
        // 生成uuid
        String uuid = UUID.randomUUID().toString();

        // 获取基本的 ValueOperations 操作对象（操作Redis的String类型）
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 1、获取锁，上锁（setne）过期时间3秒
        Boolean lock = valueOperations.setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);
        // 2、获取锁成功、查询num的值
        if (lock) {
            Object value = redisTemplate.opsForValue().get("num");
            // 2.1、判断num为空return
            if (value == null || "".equals(value)) {
                return;
            }
            // 2.2、有值就转成成int
            int num = Integer.parseInt(value + "");
            // 2.3、把redis的num加1
            redisTemplate.opsForValue().set("num", ++num);

            // 获取锁的uuid,只能释放自己的锁
            String uuidLock = (String) valueOperations.get("lock");
            // 判断比较uuid值是否一样
            if(uuid.equals(uuidLock)) {
                // 2.4、释放锁，del
                this.redisTemplate.delete("lock");
            }

        } else {
            // 3、获取锁失败、每隔0.1秒再获取
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
