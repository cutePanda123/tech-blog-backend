package com.imooc.demo.springboot.es.repository.mysql;

import com.imooc.demo.springboot.es.entity.mysql.BlogDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlBlogRepository extends JpaRepository<BlogDbEntity, Integer> {
}
