package com.imooc.demo.springboot.es.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_blog")
@Entity
public class BlogDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    @Column(columnDefinition = "mediumtext")
    private String content;
    private Date createTime;
    private Date updateTime;
}
