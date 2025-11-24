package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrator")
public class Administrator extends User {

    @Column(name = "verification_status", nullable = false)
    private boolean verificationStatus;

    public Administrator() {}

    public Administrator(Integer administrator_id, String firstname, String lastname, String login, String password, Email email, boolean verificationStatus) {
        super(administrator_id,firstname,lastname,login,password,email);
        this.verificationStatus = verificationStatus;
    }
}