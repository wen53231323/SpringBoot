package com.wen.乐观锁;

import com.wen.mapper.ProductMapper;
import com.wen.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class 模拟冲突引出锁 {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProduct01() {
        //1.小李获取商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productLi.getPrice());//100

        //2.小王获取商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王获取的商品价格为：" + productWang.getPrice());//100

        //3.小李修改商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        //4.小王修改商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);

        //5.老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板获取的商品价格为：" + productBoss.getPrice());//70
    }
}
