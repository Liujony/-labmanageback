package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.example.*.mapper")
@EnableTransactionManagement
public class XAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(XAdminApplication.class, args);
    }



}
