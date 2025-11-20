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
@Document(collection = "examiner")
public class Examiner {

    @Id
    private String examinerId;

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

    @Field(name = "verification_status")
    private boolean verificationStatus;

    @DBRef
    @Field(name = "problems")
    private List<Problem> problems;

    @DBRef
    @Field(name = "exams")
    private List<Exam> exams;

    @DBRef
    @Field(name = "relation23s")
    private List<Relation23> relation23s;

    public Examiner() {}

    public Examiner(String examinerId, String firstname, String lastname, String login, String password, String email, boolean verificationStatus) {
        this.examinerId = examinerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.verificationStatus = verificationStatus;
    }
}