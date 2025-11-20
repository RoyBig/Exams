package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "answerclosed")
@NoArgsConstructor
public class Answerclosed {

    @Id
    private String id;

    @Field(name = "description")
    private String description;

    @Field(name = "isCorrect")
    private boolean isCorrect;

    @DBRef
    @Field(name = "closedquestionQuestionid")
    private Closedquestion closedquestionQuestionid;

    public Answerclosed(String id, String description, boolean isCorrect, Closedquestion closedquestionQuestionid) {
        this.id = id;
        this.description = description;
        this.isCorrect = isCorrect;
        this.closedquestionQuestionid = closedquestionQuestionid;
    }

    @Override
    public String toString() {
        return "Answerclosed{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", isCorrect=" + isCorrect +
                ", closedquestionQuestionid=" + closedquestionQuestionid +
                '}';
    }
}