package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "relation_23")
public class Relation23 {

    @Id
    private String id;

    @DBRef
    @Field(name = "group_classid")
    private Group groupClassid;

    @DBRef
    @Field(name = "egzaminator_egzaminator")
    private Examiner egzaminatorEgzaminator;
}