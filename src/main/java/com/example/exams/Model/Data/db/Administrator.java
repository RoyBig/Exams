package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "administrator")
public class Administrator {
    @Id
    private String administratorId;

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

    public Administrator() {}

    public Administrator(String administratorId, String firstname, String lastname, String login, String password, String email, boolean verificationStatus) {
        this.administratorId = administratorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.verificationStatus = verificationStatus;
    }
}