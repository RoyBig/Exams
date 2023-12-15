package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "answerclosed")
public class Answerclosed {
    @Id
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "isCorrect")
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "closedquestion_questionid", nullable = false)
    private Closedquestion closedquestionQuestionid;

    public Answerclosed(Integer answerId, String description, boolean correct, Closedquestion question) {
        this.id = answerId;
        this.description = description;
        this.isCorrect = correct;
        this.closedquestionQuestionid = question;
    }

}