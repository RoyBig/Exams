package com.example.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EntityScan(basePackages = {
        "com.example.exams.Model.Data.db"
})
public class ExamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamsApplication.class, args);
    }

}
