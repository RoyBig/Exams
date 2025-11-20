package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "relation_25")
public class Relation25 {

    @Id
    private String id;

    @DBRef
    @Field(name = "subject_subjectid")
    private Subject subjectSubjectid;

    @DBRef
    @Field(name = "group_classid")
    private Group groupClassid;
}