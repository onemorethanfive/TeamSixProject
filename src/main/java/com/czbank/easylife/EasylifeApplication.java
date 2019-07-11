package com.czbank.easylife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.czbank.easylife.mapper")
@SpringBootApplication
public class EasylifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasylifeApplication.class, args);

    }

}
