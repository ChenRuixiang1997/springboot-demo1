package com.kgc.exam.springbootdemo1.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.kgc.exam.springbootdemo1.mapper")
public class MybatisConfiguration {
}
