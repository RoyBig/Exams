package com.example.exams.Model.Data.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection = "student")
@AllArgsConstructor
public class Student {

    @Id
    private String studentId;

    @DBRef
    @Field(name = "groups")
    private List<Group> groups;

    @DBRef
    @Field(name = "exams")
    @DocumentReference(lookup = "{ 'students.$id' : ?#{#self._id} }")
    private List<Exam> exams;

    @Field(name = "firstname")
    private String firstname;

    @Field(name = "lastname")
    private String lastname;

    @Field(name = "login")
    private String login;

    @Field(name = "password")
    private String password;

    @Field(name = "email")
    private String email;

    @DBRef
    @Field(name = "problems")
    private List<Problem> problems;

    @DBRef
    @Field(name = "studentopenanswers")
    private List<Studentopenanswer> studentopenanswers;

    @DBRef
    @Field(name = "studentclosedanswer")
    private List<Studentclosedanswer> studentclosedanswer;

    @DBRef
    @Field(name = "logstudentexam")
    private List<Logstudentexam> logstudentexam;

    public Student() {}

    public Student(String studentId, String firstname, String lastname, String login, String password, String email) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}