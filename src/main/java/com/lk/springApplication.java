package com.lk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lk.Mapper")
@SpringBootApplication()
public class springApplication {
	public static void main(String[] args){
		SpringApplication.run(springApplication.class, args);
	}
}
