package SpringDataRedis集成连接;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Zset_Test {
    //注入键值都为String对象的RedisTemplate对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void test(){
        //测试数据，在Redis客户端执行
        //zadd myzsetA -5 && -2 ## 0 @@ 1 aa 5 bb 10 cc 15 dd 20 ee 25 ff 30 gg 35 hh 40 ii 45 jj 50 kk
        //zadd chinas -10 Anhui 45 Shanghai 24 Beijing 33 Henan 87 Guangdong
        //zadd zsetA 20 zhangsan 25 lisi 33 wanger 15 mazi 33 babao 23 xiechao
        //zadd zsetB 5 zhangsan 10 lisi 15 mazi 20 babao
        //zadd zsetC 10 aa 10 bb 10 cc 10 dd 10 ee 10 ff 10 gg 10 hh

        //获取基本的 ZSetOperations 操作对象（操作Redis的ZSet类型）
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        //往有序集合里面插入一个元素
        Boolean add1 = opsForZSet.add("ChinaA", "Beijing", 23D);
        //往有序集合内部批量插入元素(返回添加成功的元素个数)
        Set<ZSetOperations.TypedTuple<String>> batchKV1 = new HashSet<>();
        batchKV1.add(ZSetOperations.TypedTuple.of("Shanghai", 22D));
        batchKV1.add(ZSetOperations.TypedTuple.of("Anhui", 27D));
        Long add2 = opsForZSet.add("ChinaA", batchKV1);
        //往有序集合里面插入一个或多个元素（若当前集合存在此元素则不会添加）(返回添加成功的个数或返回true|false)
        Boolean aBoolean = opsForZSet.addIfAbsent("ChinaA", "Beijing", 40D);
        batchKV1.add(ZSetOperations.TypedTuple.of("Jiangsu", 66D));
        Long chinaA = opsForZSet.addIfAbsent("ChinaA", batchKV1);
        //对有序集合中的某个元素进行分数的累加 返回累加后的元素分数，此时我对“Beijing”累加10，返回33.0
        Double increment = opsForZSet.incrementScore("ChinaA", "Beijing", 10D);

        //返回有序集合中指定范围的元素个数，根据分数条件
        Long count1 = opsForZSet.count("myzsetA", 10D, 35D);
        //返回有序集合中指定范围的元素个数，根据lex元素条件（红面方法有提到）
        RedisZSetCommands.Range rq = RedisZSetCommands.Range.range();
        rq.gt("bb");
        rq.lt("ff");
        Long count2 = opsForZSet.lexCount("zsetC", rq);

        //返回当前元素“Beijing”在集合“chinas”中的位置（从小到大） 返回1
        Long rank1 = opsForZSet.rank("chinas", "Beijing");
        //返回当前元素“Beijing”在集合“chinas”中的位置(从大到小)  返回3
        Long rank2 = opsForZSet.reverseRank("chinas", "Beijing");

        //返回集合中元素个数
        Long size1 = opsForZSet.size("chinas");
        Long size2 = opsForZSet.zCard("chinas");
        //返回有序集合中指定元素的分数
        Double score = opsForZSet.score("chinas", "Shanghai");
        //设置查询条件并且查询有序集合chinas的全部元素”*“，为什么count没起效果没太注意
        ScanOptions build = ScanOptions.scanOptions().match("*").count(2L).build();
        Cursor<ZSetOperations.TypedTuple<String>> scan = opsForZSet.scan("chinas", build);
        while (scan.hasNext()) {
            ZSetOperations.TypedTuple<String> next = scan.next();
            System.out.println(next.getValue() + " : " + next.getScore());
        }
        //返回基本操作方法对象
        RedisOperations<String, String> operations = opsForZSet.getOperations();
    }

    //关于ZSetOperations查询Range及reverseRange
    //  Range：分数按照从低到高查询
    //  reverseRange：分数从高到地查询
    @Test
    void redisTemplateBaseRange() {
        //关于 lex 查询
        //创建关于lex的查询方式
        //若某个有序集合使用元素查询时（lex），那么我推荐你最好使用分数都是相同的有序集合！
        // RedisZSetCommands.Range对象说明：
        // RedisZSetCommands.Range rq = RedisZSetCommands.Range.range();
        //      rq.gt("bb"); 代表查询条件大于 “bb”  对于命令 (bb
        //      rq.lt("ff"); 代表查询条件小于 “ff”  对于命令 (ff
        //      rq.gte("bb"); 代表查询条件大于等于 “bb”  对于命令 [bb
        //      rq.lte("ff"); 代表查询条件小于等于 “ff”  对于命令 [ff
        //      rq.getMax();  代表查询条件从最大     对于命令 +
        //      rq.getMin();  代表查询条件从最小     对于命令 -
        // RedisZSetCommands.Limit对象说明：
        // RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        //      limit.count(2); 代表查询2个元素
        //      limit.offset(3); 代表查询偏移量3，就是从第4个开始，必须存在count属性，偏移后查询几个

        //获取基本的 ZSetOperations 操作对象（操作Redis的ZSet类型）
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        //基本方式查询，0 -1 代表查询全部
        Set<String> range1 = opsForZSet.range("zsetA", 0L, -1L);
        //关于 score 查询
        //分数查询，查询集合元素分数在1~45的元素
        Set<String> range2 = opsForZSet.rangeByScore("myzsetA", 1D, 45D);
        //分数查询，查询集合元素分数在1~45的元素，偏移量1，查询数3（就是第二个开始查询，查询3个）
        Set<String> range3 = opsForZSet.rangeByScore("myzsetA", 1D, 45D, 1L, 3L);
        //条件
        RedisZSetCommands.Range rq = RedisZSetCommands.Range.range();
        rq.getMin();
        rq.getMax();
        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        limit.count(2);
        limit.offset(3);
        //查询有序集合zsetC的全部元素 - +
        Set<String> range4 = opsForZSet.rangeByLex("zsetC", rq);
        //查询有序集合zsetC的全部元素 - +  并且返回偏移3，count = 2
        Set<String> range5 = opsForZSet.rangeByLex("zsetC", rq, limit);
        //查询有序集合chinas分数在-30 ~ 40之间的全部元素（返回元素和分数）
        Set<ZSetOperations.TypedTuple<String>> range6 = opsForZSet.rangeByScoreWithScores("chinas", -30D, 40D);
        Iterator<ZSetOperations.TypedTuple<String>> iterator = range6.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<String> next = iterator.next();
            System.out.println(next.getValue() + " : " + next.getScore());
        }
        //查询有序集合chinas分数在-30 ~ 40之间的全部元素  并且返回偏移1，count = 2
        Set<ZSetOperations.TypedTuple<String>> range7 = opsForZSet.rangeByScoreWithScores("chinas", -30D, 40D, 1L, 2L);
        //查询有序集合chinas分数在 - + （全部元素）
        Set<ZSetOperations.TypedTuple<String>> range8 = opsForZSet.rangeWithScores("chinas", 0L, -1L);

        // reverseRange
        // 关于：reverseRange的一套方法和上面的几个方法使用上一样，只不过输出的是从大到小的返回
        //从大到小的基本方式查询，0 -1 代表查询全部
        Set<String> reverse1 = opsForZSet.reverseRange("myzsetA", 0L, -1L);
    }

    //关于ZSetOperations查询差异查询
    @Test
    void redisTemplateBaseDiff() {
        //指定交集、并集的结果集的聚合方式：
        //  枚举：RedisZSetCommands.Aggregate.MIN 指定min则交集并集的元素的分数取最小
        //  枚举：RedisZSetCommands.Aggregate.MAX 指定max则交集并集的元素的分数取最大
        //  枚举：RedisZSetCommands.Aggregate.SUM 指定sum（默认）则交集并集的元素的分数结合
        //RedisZSetCommands.Weights:
        //  RedisZSetCommands.Weights.fromSetCount(2)：输入排序集的集合计算，有几个集合就写几
        //  RedisZSetCommands.Weights.of() :里面传入int或者double（一种）的可变参，有几个集合就传几个（做乘法计算）

        //获取基本的 ZSetOperations 操作对象（操作Redis的ZSet类型）
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        //有序集合“zsetA” 对比 “zsetB” 获取交集数据存放到"interZSetA"集合上（分数为两个元素的相加）
        Long intersect1 = opsForZSet.intersectAndStore("zsetA", "zsetB", "interZSetA");
        //以有序集合“zsetA”为主对比其它一个或多个集合的交集（接收集合keys）
        Long intersect2 = opsForZSet.intersectAndStore("zsetA", Arrays.asList("zsetB"), "interZSetA");
        // Aggregate为max（代表并、交集取最大分数）
        Long intersect3 = opsForZSet.intersectAndStore("zsetA", Arrays.asList("zsetB"), "interZSetB",
                RedisZSetCommands.Aggregate.MAX);
        // Weights权重计算（乘法因子）
        Long intersect4 = opsForZSet.intersectAndStore("zsetA", Arrays.asList("zsetB"), "interZSetC",
                RedisZSetCommands.Aggregate.MAX,
                RedisZSetCommands.Weights.of(5, 10));
        //unionAndStore
        //关于：unionAndStore 并集方法参考上面四个，用法一样
        //并集就是把几个集合的元素并到一起（不漏任何元素），然后单个元素单独计算，多个元素计算后合并到一起
    }

    //删除方法
    @Test
    void redisTemplateBaseRemove() {
        //获取基本的 ZSetOperations 操作对象（操作Redis的ZSet类型）
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        //删除有序集合chinas里两个元素，返回删除成功的个数
        Long remove1 = opsForZSet.remove("chinas", "Shanghai", "Henan");
        //删除有序集合chinas里全部元素 0 -1
        Long remove2 = opsForZSet.removeRange("chinas", 0L, -1L);
        //删除有序集合myzsetA里5 ~ 30 之间的元素
        Long remove3 = opsForZSet.removeRangeByScore("myzsetA", 5D, 30D);
        //删除有序集合zsetC里全部元素
        RedisZSetCommands.Range rq = RedisZSetCommands.Range.range();
        rq.getMin();
        rq.getMax();
        Long remove4 = opsForZSet.removeRangeByLex("zsetC", rq);
    }

}
