package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_classid")
    private Group groupClassid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_examid")
    private Exam examExamid;

    @Column(name = "accountid", nullable = false)
    private Integer accountid;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "lastname", length = 20)
    private String lastname;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "email", length = 20)
    private String email;

}