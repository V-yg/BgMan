package com.yiie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yiie.commmon.mapper")
public class BgmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(BgmanApplication.class, args);
    }

}
