package com.example.springbootmybatismapper;

import com.example.springbootmybatismapper.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@MapperScan(basePackages = "com.example.springbootmybatismapper.dao", markerInterface = MyMapper.class)
public class SpringBootMybatisMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMapperApplication.class, args);
	}
}
