package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "subject")
public class Subject {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    public Subject() {}

    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }
}