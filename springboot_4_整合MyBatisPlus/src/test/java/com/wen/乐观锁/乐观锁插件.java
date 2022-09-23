package com.wen.乐观锁;

import com.wen.mapper.ProductMapper;
import com.wen.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class 乐观锁插件 {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProduct01() {

        //1.小李获取商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productLi.getPrice());

        //2.小王获取商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productWang.getPrice());

        //3.小李修改商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        //4.小王修改商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        int result = productMapper.updateById(productWang);
        // 若result为0说明操作失败，执行重试
        if (result == 0) {
            // 查询数据，保证获得的版本号为最新的
            Product productNew = productMapper.selectById(1);
            // 执行更新
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }

        //5.老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板获取的商品价格为：" + productBoss.getPrice());
    }
}
