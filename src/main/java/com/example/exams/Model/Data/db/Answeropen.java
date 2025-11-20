package com.example.exams.Model.Data.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "answeropen")
public class Answeropen {

    @Id
    private String id;

    @Field(name = "description")
    private String description;

    @DBRef
    @Field(name = "openquestionQuestionid")
    private OpenQuestion openquestionQuestionid;

    @Override
    public String toString() {
        return "Answeropen{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", openquestionQuestionid=" + openquestionQuestionid +
                '}';
    }
}