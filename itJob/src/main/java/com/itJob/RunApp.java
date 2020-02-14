package com.itJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Description: 启动类 类
 * @Author: LRJ
 * @Date: 2019/11/27 13:46
 */
@SpringBootApplication
@MapperScan("com.itJob.mapper")
public class RunApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RunApp.class, args);
    }
}