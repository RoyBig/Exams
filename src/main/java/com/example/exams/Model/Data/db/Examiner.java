package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "examiner")
public class Examiner extends User{

    @Column(name = "verification_status", nullable = false)
    private boolean verificationStatus;

    @OneToMany(mappedBy = "problemsExaminer",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Problem> problems;

    @OneToMany(mappedBy = "conductingExaminer",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Exam> exams;

    @OneToMany(mappedBy = "egzaminatorEgzaminator",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Relation23> relation23s;

    public Examiner() {}

    public Examiner(Integer examiner_id, String firstname, String lastname, String login, String password, Email email, boolean verificationStatus) {
        super(examiner_id,firstname,lastname,login,password,email);
        this.verificationStatus = verificationStatus;
    }
}