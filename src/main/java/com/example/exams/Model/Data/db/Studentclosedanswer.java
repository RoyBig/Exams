package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "studentclosedanswer")
public class Studentclosedanswer {

    @Id
    private String id;

    @Field(name = "correctness")
    private Boolean correctness;

    @Field(name = "date")
    private LocalDate date;

    @DBRef
    @Field(name = "answerclosed_answerid")
    private Answerclosed answerclosedAnswerid;

    @DBRef
    @Field(name = "closedquestion_questionid")
    private Closedquestion closedquestionQuestionid;

    @DBRef
    @Field(name = "student_student")
    private Student studentStudent;

    public Studentclosedanswer(String id, Boolean correctness, LocalDate date, Answerclosed answerclosedAnswerid, Closedquestion closedquestionQuestionid, Student studentStudent) {
        this.id = id;
        this.correctness = correctness;
        this.date = date;
        this.answerclosedAnswerid = answerclosedAnswerid;
        this.closedquestionQuestionid = closedquestionQuestionid;
        this.studentStudent = studentStudent;
    }

    public Studentclosedanswer() {

    }

    @Override
    public String toString() {
        return "Studentclosedanswer{" +
                "id='" + id + '\'' +
                ", correctness=" + correctness +
                ", date=" + date +
                ", answerclosedAnswerid=" + answerclosedAnswerid +
                ", closedquestionQuestionid=" + closedquestionQuestionid +
                ", studentStudent=" + studentStudent +
                '}';
    }
}