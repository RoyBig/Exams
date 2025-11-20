package com.example.exams.Model.Data.db;

import com.example.exams.Model.Data.ProperDataModels.ProblemCategories;
import com.example.exams.Model.Data.ProperDataModels.ProblemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "problem")
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    @Id
    private String id;

    @Field(name = "photo")
    private byte[] photo;

    @Field(name = "description")
    private String description;

    @DBRef
    @Field(name = "problems_student")
    private Student problemsStudent;

    @DBRef
    @Field(name = "problems_examiner")
    private Examiner problemsExaminer;

    @Field(name = "category")
    private ProblemCategories category;

    @Field(name = "username")
    private String username;

    @Field(name = "status")
    private ProblemStatus status = ProblemStatus.New;
}