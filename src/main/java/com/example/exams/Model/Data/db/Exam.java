package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "exam")
public class Exam {

    @Id
    private String id;

    @Field(name = "question_pool")
    private Integer questionPool;

    @Field(name = "question_pool_strategy")
    private Boolean questionPoolStrategy;

    @Field(name = "description")
    private String description;

    @Field(name = "start_date")
    private LocalDate startDate;

    @Field(name = "start_time")
    private LocalTime startTime;

    @Field(name = "end_date")
    private LocalDate endDate;

    @Field(name = "end_time")
    private LocalTime endTime;

    @Field(name = "duration")
    private Long duration;

    @Field(name = "visibility")
    private Boolean visibility;

    @DBRef
    @Field(name = "exams_subject")
    private Subject examsSubject;

    @DBRef
    @Field(name = "conducting_examiner")
    private Examiner conductingExaminer;

    @DBRef
    @Field(name = "students")
    private List<Student> students;

    public Exam() {
    }

    public Exam(String id, Integer questionPool, Boolean questionPoolStrategy, String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Subject examsSubject, List<Student> students) {
        this.id = id;
        this.questionPool = questionPool;
        this.questionPoolStrategy = questionPoolStrategy;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.visibility = false;
        this.examsSubject = examsSubject;
        this.students = students;
        this.duration = Duration.between(LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime)).toMinutes();
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id='" + id + '\'' +
                ", questionPool=" + questionPool +
                ", questionPoolStrategy=" + questionPoolStrategy +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", visibility=" + visibility +
                ", examsSubject=" + examsSubject +
                ", conductingExaminer=" + conductingExaminer +
                ", students=" + students +
                '}';
    }
}