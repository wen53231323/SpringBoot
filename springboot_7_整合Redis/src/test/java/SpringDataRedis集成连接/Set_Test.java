package SpringDataRedis集成连接;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Set_Test {
    //注入键值都为String对象的RedisTemplate对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        //获取基本的 SetOperations 操作对象（操作Redis的Set类型）
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();

        //往ChinaA的Set集合中添加 北京、上海、安徽、河南、广东、江苏；并返回添加成功的个数（已存在的不会被添加）；ChinaB一样
        Long add1 = opsForSet.add("ChinaA", "Beijing", "Shanghai", "Anhui", "Henan", "Guangdong", "Jiangsu");
        Long add2 = opsForSet.add("ChinaB", "Shanxi", "Shanghai", "Fujian", "Anhui", "Zhejiang");
        //===================================================================
        //差异比较
        //=====返回第一个集合与其它集合之间的差异；说白就是第一个集合的某个元素在其它集合都不存在则这个元素会被返回，
        // key1 = {a,b,c,d}
        // key2 = {c}
        // key3 = {a,c,e}
        // SDIFF key1 key2 key3  = {b,d}
        //第一个集合和其它集合比较差异，并返回有差异的元素
        Set<String> difference1 = opsForSet.difference("ChinaA", "ChinaB");
        Set<String> difference2 = opsForSet.difference("ChinaA", Arrays.asList("ChinaB"));
        Set<String> difference3 = opsForSet.difference(Arrays.asList("ChinaA", "ChinaB"));
        //第一个集合和其它集合比较差异，并把差异的元素存放到指定的集合中（注：存放差异的集合会把原来的数据覆盖）
        Long aLong1 = opsForSet.differenceAndStore("ChinaA", "ChinaB", "diffStoreA");
        Long aLong2 = opsForSet.differenceAndStore("ChinaA", Arrays.asList("ChinaB"), "diffStoreB");
        Long aLong3 = opsForSet.differenceAndStore(Arrays.asList("ChinaA", "ChinaB"), "diffStoreC");
        //=====返回第一个集合与其它集合之间的交集；说白就是第一个集合的某个元素在其它集合都存在则这个元素会被返回，
        // key1 = {a,b,c,d}
        // key2 = {c}
        // key3 = {a,c,e}
        // SINTER key1 key2 key3 = {c}
        // 使用上和上面6个方法一样，只不过这个是获取它的 交集
        Set<String> intersect1 = opsForSet.intersect("ChinaA", "ChinaB");
        Set<String> intersect2 = opsForSet.intersect("ChinaA", Arrays.asList("ChinaB"));
        Set<String> intersect3 = opsForSet.intersect(Arrays.asList("ChinaA", "ChinaB"));
        Long iLong1 = opsForSet.intersectAndStore("ChinaA", "ChinaB", "interStoreA");
        Long iLong2 = opsForSet.intersectAndStore("ChinaA", Arrays.asList("ChinaB"), "interStoreB");
        Long iLong3 = opsForSet.intersectAndStore(Arrays.asList("ChinaA", "ChinaB"), "interStoreC");
        //=====用于返回所有给定集合的并集
        // key1 = {a,b,c,d}
        // key2 = {c}
        // key3 = {a,c,e}
        // sunion key1 key2 key3 = {a,b,c,d,e}
        // 使用上和上面12个方法一样，只不过这个是获取它的 并集
        Set<String> union1 = opsForSet.union("ChinaA", "ChinaB");
        Set<String> union2 = opsForSet.union("ChinaA", Arrays.asList("ChinaB"));
        Set<String> union3 = opsForSet.union(Arrays.asList("ChinaA", "ChinaB"));
        Long uLong1 = opsForSet.unionAndStore("ChinaA", "ChinaB", "unionStoreA");
        Long uLong2 = opsForSet.unionAndStore("ChinaA", Arrays.asList("ChinaB"), "unionStoreB");
        Long uLong3 = opsForSet.unionAndStore(Arrays.asList("ChinaA", "ChinaB"), "unionStoreC");
        //===================================================================
        //返回集合中全部元素个数
        Long size = opsForSet.size("ChinaA");
        //返回集合中全部元素的set集合
        Set<String> chinaA = opsForSet.members("ChinaA");
        //判断集合中是否存在“Shanghai”这个元素
        Boolean member = opsForSet.isMember("ChinaA", "Shanghai");
        //随机返回集合中一个或多个元素(注意返回多个元素可能会存在重复的)
        String s = opsForSet.randomMember("ChinaA");
        List<String> strings = opsForSet.randomMembers("ChinaA", 3);
        //随机返回集合中一个或多个元素(注意返回多个元素不会存在重复的)
        Set<String> chinaA2 = opsForSet.distinctRandomMembers("ChinaA", 3L);
        //将ChinaA集合里的Beijing弹出放到ChinaB集合中（若A集合弹出元素在B集合存在，那么A集合元素也将被清理）
        Boolean move = opsForSet.move("ChinaA", "Shanghai", "ChinaB");
        //带条件的获取指定元素
        ScanOptions scanOptions = ScanOptions.scanOptions().match("*e*").count(2L).build();
        Cursor<String> chinaA1 = opsForSet.scan("ChinaA", ScanOptions.NONE);
        while (chinaA1.hasNext()) {
            System.out.println(chinaA1.next());
        }
        //获取RedisTemplate基本操作方法对象
        RedisOperations<String, String> operations = opsForSet.getOperations();
        //双重ChinaA集合中指定的元素，返回删除成功的元素个数
        Long remove = opsForSet.remove("ChinaA", "Guangdong", "Beijing", "LboLa");
        //从集合ChinaA中随机弹出(删除)一个或者多个元素，并返回被弹出的元素名称
        String pop1 = opsForSet.pop("ChinaA");
        List<String> pop2 = opsForSet.pop("ChinaA", 2L);
    }

}
