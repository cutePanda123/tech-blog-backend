package com.imooc.demo.springboot.es.controller;

import com.imooc.demo.springboot.es.entity.es.BlogEsEntity;
import com.imooc.demo.springboot.es.entity.mysql.BlogDbEntity;
import com.imooc.demo.springboot.es.repository.mysql.MySqlBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {
    @Autowired
    MySqlBlogRepository mySqlBlogRepository;
    @Autowired
    ElasticsearchRepository elasticsearchRepository;

    @GetMapping("/blogs")
    public Object blog() {
        List<BlogDbEntity> blogs = mySqlBlogRepository.findAll();
        return blogs;
    }

    @GetMapping("/search")
    public Object search(@RequestBody Param param) {
        String option = param.getSearchOption();
        String keyword = param.getSearchKeyword();
        Map<String, Object> map = new HashMap<>();
        StopWatch watch = new StopWatch();
        watch.start();
        if (option.equalsIgnoreCase("mysql")) {
            List<BlogDbEntity> list = mySqlBlogRepository.queryWithKeyword(keyword);
            map.put("list", list);
        } else if (option.equalsIgnoreCase("es")) {
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title", keyword));
            builder.should(QueryBuilders.matchPhraseQuery("content", keyword));
            String query = builder.toString();
            System.out.println((query));
            Page<BlogEsEntity> page = (Page<BlogEsEntity>) elasticsearchRepository.search(builder);
            List<BlogEsEntity> list = page.getContent();
            map.put("list", list);
        } else {
            return "invalid search option";
        }
        watch.stop();
        map.put("duraton", watch.getTotalTimeMillis());

        return map;
    }

    @GetMapping("/blog/{id}")
    public Object blog(@PathVariable("id") Integer id) {
        return mySqlBlogRepository.findById(id).get();
    }

    @Data
    public static class Param {
        private String searchOption;
        private String searchKeyword;
    }
}
