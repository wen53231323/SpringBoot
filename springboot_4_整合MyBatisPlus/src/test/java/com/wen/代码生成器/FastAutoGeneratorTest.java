package com.wen.代码生成器;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.List;

public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                "jdbc:mysql://127.0.0.1:3306/ssm? characterEncoding=utf-8&userSSL=false",
                "root",
                "123456"
        ).globalConfig(builder -> {// globalConfig全局配置
            builder.author("wen") // 设置作者
                    //.enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("E://mybatis_plus"); // 指定输出目录
        }).packageConfig(builder -> {// packageConfig包配置
            builder.parent("com.wen") // 设置父包名
                    .moduleName("MyBatisPlus") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E://mybatis_plus"));// 设置映射文件mapperXml生成路径
        }).strategyConfig(builder -> {// strategyConfig策略配置
            builder.addInclude("t_user") // 设置需要生成的表名
                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
        }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                .execute();

    }

}
