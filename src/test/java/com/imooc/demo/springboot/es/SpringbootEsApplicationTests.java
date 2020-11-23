package com.imooc.demo.springboot.es;

import com.imooc.demo.springboot.es.entity.es.BlogEsEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootEsApplicationTests {
	@Autowired
	ElasticsearchRepository elasticsearchRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void elasticserachRepositoryTest() {
		Iterable<BlogEsEntity> entities = elasticsearchRepository.findAll();
		for (BlogEsEntity entity : entities) {
			System.out.println(entity);
		}
	}

}
