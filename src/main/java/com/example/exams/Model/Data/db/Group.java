package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection = "group")
public class Group {

    @Id
    private String id;

    @Field(name = "code")
    private String code;

    @DBRef
    @Field(name = "students")
    private List<Student> students;

    public Group() {}

    public Group(String code, List<Student> students) {
        this.code = code;
        this.students = students;
    }

    public Group(String id, String code, List<Student> students) {
        this.id = id;
        this.code = code;
        this.students = students;
    }
}