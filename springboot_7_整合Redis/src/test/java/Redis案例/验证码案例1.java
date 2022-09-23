package Redis案例;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;

public class 验证码案例1 {


    @Test
    public void testVerifyPhone() {
        // 模拟发送验证码
        verifyPhone("123456");
    }

    @Test
    public void testVerifyCode() {
        // 模拟校验验证码
        verifyCode("123456", "664993");
    }

    /**
     * ① 生成随机的6位验证码
     */
    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            // 生成10以内的随机整数
            int rand = random.nextInt(10);
            // 拼接随机数
            code += rand;
        }
        return code;
    }

    /**
     * ② 发送验证码：每个手机号每天只能发送 3 次，并将验证码存储到 Redis 中，设置过期时间两分钟
     *
     * @param phone 手机号
     */
    public static void verifyPhone(String phone) {
        // 与Redis建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 输入Redis密码
        jedis.auth("123456");

        // 手机发送次数key
        String countKey = "VerifyCode" + phone + ":count";
        String count = jedis.get(countKey);

        if (count == null) {// 如果为null，说明第一次发送，则设置验证码到Redis，给定生存时间和发送次数
            // 设置发送次数1，生存时间24小时
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {// 如果发送次数小于3次，则将发送次数增加1
            // 发送次数+1（给指定 key 对应的 value 加 1）
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {// 如果发送次数大于3次，则抛出异常提示
            throw new RuntimeException(phone + "，您今天发送验证码次数已经超过了 3 次");
        }


        // 验证码的key
        String codeKey = "VerifyCode" + phone + ":code";
        // 生成随机6位验证码
        String vcode = getCode();
        System.out.println("随机生成的验证码为："+vcode);
        // 把验证码放到Redis里，设置过期时间为120秒
        jedis.setex(codeKey, 120, vcode);
        jedis.close();
    }


    /**
     * ③ 校验验证码
     *
     * @param phone     手机号
     * @param inputCode 用户输入的验证码
     */
    public static void verifyCode(String phone, String inputCode) {
        // 与Redis建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 输入Redis密码
        jedis.auth("123456");

        // 从Redis获取验证码的 key
        String codeKey = "VerifyCode" + phone + ":code";
        // 保存验证码
        String redisCode = jedis.get(codeKey);

        if (redisCode.equals(inputCode)) {
            System.out.println("验证码校验成功");
        } else {
            throw new RuntimeException(phone + "，您输入的验证码错误或已经过期，请重新检查后再输入");
        }

        jedis.close();
    }
}
