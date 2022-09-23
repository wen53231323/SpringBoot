package com.wen.pojo;
//lombok

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表get、set、toString、equals、hashCode等操作
@Data
// 代表无参构造
@NoArgsConstructor
// 代表全参构造
@AllArgsConstructor
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;
}
