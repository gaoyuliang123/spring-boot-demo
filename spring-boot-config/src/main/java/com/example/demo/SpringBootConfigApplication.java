package com.example.demo;

import com.example.demo.config.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigBean.class)
public class SpringBootConfigApplication {
	/**
	 *
	 * 启动项目有三种方式
	 * 1、main方法
	 * 2、mvn Plugins spring-boot:run
	 * 3、mvn package 打包，会打包成一个可以直接运行的 JAR 文件，使用“ java -jar spring-boot-hello-0.0.1-SNAPSHOT.jar”命令就可以直接运行
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigApplication.class, args);
	}
}
