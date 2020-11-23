package com.imooc.demo.springboot.es.repository.mysql;

import com.imooc.demo.springboot.es.entity.mysql.BlogDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MySqlBlogRepository extends JpaRepository<BlogDbEntity, Integer> {
    @Query("select e from BlogDbEntity e order by e.createTime desc")
    public List<BlogDbEntity> queryAll();

    @Query("select e from BlogDbEntity e where e.title " +
            "like concat('%',:keyword,'%') or e.content like concat('%',:keyword,'%')")
    public List<BlogDbEntity> queryWithKeyword(@Param("keyword") String keyword);
}
