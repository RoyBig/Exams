package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "servicestatistics")
public class Servicestatistic {

    @Id
    private String id;

    @Field(name = "visitorscount")
    private Integer visitorscount;

    @Field(name = "examscount")
    private Integer examscount;

    @Field(name = "studentscount")
    private Integer studentscount;

    @Field(name = "examinatorscount")
    private Integer examinatorscount;

    @Field(name = "startdate")
    private LocalDate startdate;

    public Servicestatistic() {
    }

    public Servicestatistic(Integer visitorscount, Integer examscount, Integer studentscount, Integer examinatorscount) {
        this.visitorscount = visitorscount;
        this.examscount = examscount;
        this.studentscount = studentscount;
        this.examinatorscount = examinatorscount;
        this.startdate = LocalDate.now();
    }
}