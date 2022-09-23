package Redis案例;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;

public class 验证码案例2 {
    @Test
    public void testVerifyPhone() {
        // 模拟发送验证码，手机号过期时间1天，验证码过期时间两分钟
        verifyPhone("123456", 24 * 60 * 60, 2 * 60);
    }

    @Test
    public void testVerifyCode() {
        // 模拟校验验证码
        verifyCode("123456", "173679");
    }

    /**
     * ① 生成验证码
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
     * ② 发送验证码：每个手机号每天只能发送 3 次，并将验证码存储到 Redis 中
     *
     * @param phone       手机号
     * @param phoneExpire 手机号过期时间
     * @param codeExpire  验证码过期时间
     */
    public static void verifyPhone(String phone, Integer phoneExpire, Integer codeExpire) {
        // 与Redis建立连接，Jedis(主机地址,端口号)
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        // 输入Redis密码
        jedis.auth("123456");

        // 手机发送次数的 key
        String phoneCountKey = "VerifyCode:" + phone + ":count";

        // 每个手机号每天只能发送 3 次
        String phoneCount = jedis.get(phoneCountKey);
        if (null == phoneCount) {// 如果为null，说明第一次发送，则设置验证码到Redis，给定生存时间和发送次数
            jedis.setex(phoneCountKey, phoneExpire, "1");
        } else if (Integer.parseInt(phoneCount) <= 2) {// 如果发送次数小于3次，则将发送次数增加1
            // 发送次数+1（incr：给指定 key 对应的 value 加 1）
            jedis.incr(phoneCountKey);
        } else {// 如果发送次数打印3次，则关闭连接，并提示
            throw new RuntimeException(phone + "，您今天发送验证码次数已经超过了 3 次");
        }

        // 验证码的 key
        String codeKey = "VerifyCode:" + phone + ":code";
        // 生成随机6位验证码
        String vcode = getCode();
        System.out.println("随机生成的验证码为："+vcode);
        // 把验证码放到Redis里，设置过期时间
        jedis.setex(codeKey, codeExpire, getCode());

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

        // 验证码的 key
        String codeKey = "VerifyCode:" + phone + ":code";
        // 保存验证码
        String vCode = jedis.get(codeKey);
        if (inputCode.equalsIgnoreCase(vCode)) {
            System.out.println("验证码校验成功");
        } else {
            throw new RuntimeException(phone + "，您输入的验证码错误或已经过期，请重新检查后再输入");
        }
    }
}
