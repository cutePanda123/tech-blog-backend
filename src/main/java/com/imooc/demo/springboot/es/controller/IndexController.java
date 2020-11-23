package com.imooc.demo.springboot.es.controller;

import com.imooc.demo.springboot.es.entity.mysql.BlogDbEntity;
import com.imooc.demo.springboot.es.repository.mysql.MySqlBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MySqlBlogRepository mySqlBlogRepository;

    @RequestMapping("/")
    public  String index() {
        List<BlogDbEntity> list = mySqlBlogRepository.findAll();
        System.out.println(list.size());
        return "index.html";
    }
}
