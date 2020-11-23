package com.imooc.demo.springboot.es.repository.es;

import com.imooc.demo.springboot.es.entity.es.BlogEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchBlobRepository extends ElasticsearchRepository<BlogEsEntity, Integer> {
}
