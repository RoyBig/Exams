package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Document(collection = "logs")
public class Log {

    @Id
    private String id;

    @Field(name = "description")
    private String description;

    @Field(name = "date")
    private LocalDate date;

    @Field(name = "time")
    private LocalTime time;

    public Log() {
    }

    public Log(String description) {
        this.description = description;
        this.date = LocalDate.now();
        this.time = LocalTime.now().withNano(0);
    }
}