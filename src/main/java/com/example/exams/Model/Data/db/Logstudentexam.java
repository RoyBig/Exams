package com.example.exams.Model.Data.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Document(collection = "logstudentexam")
@AllArgsConstructor
@NoArgsConstructor
public class Logstudentexam {

    @Id
    private String id;

    @Field(name = "description")
    private String description;

    @Field(name = "date")
    private LocalDate date;

    @Field(name = "time")
    private LocalTime time;

    @Field(name = "score_result")
    private Integer scoreresult;

    @DBRef
    @Field(name = "exam_examid")
    private Exam examExamid;

    @DBRef
    @Field(name = "student_studentid")
    private Student studentStudent;

    public void addPoints(int points) {
        if (this.scoreresult == null) {
            this.scoreresult = 0;
        }
        this.scoreresult += points;
    }
}