package com.baqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuyandeng
 * @date 2025/1/3
 * @Description
 */
@SpringBootApplication
@MapperScan("com.baqn.mapper")
public class BdqnServeApplication {
  public static void main(String[] args) {
    SpringApplication.run(BdqnServeApplication.class, args);
  }
}
