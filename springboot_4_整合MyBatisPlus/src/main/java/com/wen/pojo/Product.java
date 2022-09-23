package com.wen.pojo;

import com.baomidou.mybatisplus.annotation.Version;

public class Product {
    private Long id; // 主键ID
    private String name; // 商品名称
    private Integer price; // 价格
    // @Version注解：乐观锁注解，标记乐观锁版本号字段
    @Version
    private Integer version; // 乐观锁版本号

    public Product() {
    }

    public Product(Long id, String name, Integer price, Integer version) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", version=" + version +
                '}';
    }
}
