package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Document(collection = "studentopenanswer")
public class Studentopenanswer {

    @Id
    private String id;

    @Field(name = "score")
    private Integer score;

    @Field(name = "date")
    private LocalDate date;

    @Field(name = "time")
    private LocalTime time;

    @Field(name = "description")
    private String description;

    @DBRef
    @Field(name = "openquestion_questionid")
    private OpenQuestion openquestionQuestionid;

    @DBRef
    @Field(name = "student_student")
    private Student studentStudent;

    public Studentopenanswer(String id, Integer score, LocalDate date, LocalTime time, String description, OpenQuestion openquestionQuestionid, Student studentStudent) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.time = time;
        this.description = description;
        this.openquestionQuestionid = openquestionQuestionid;
        this.studentStudent = studentStudent;
    }

    public Studentopenanswer() {
    }

    @Override
    public String toString() {
        return "Studentopenanswer{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", openquestionQuestionid=" + openquestionQuestionid +
                ", studentStudent=" + studentStudent +
                '}';
    }
}