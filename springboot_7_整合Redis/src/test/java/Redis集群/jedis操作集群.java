package Redis集群;

import com.wen.RedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@SpringBootTest(classes = RedisApplication.class)
public class jedis操作集群 {
    @Test
    public void set() {
        HostAndPort hostAndPort = new HostAndPort("192.168.10.102", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);
        jedisCluster.set("name", "小明");
        jedisCluster.close();
    }

    @Test
    public void get() {
        HostAndPort hostAndPort = new HostAndPort("192.168.10.102", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);
        jedisCluster.get("name");
        jedisCluster.close();
    }
}
