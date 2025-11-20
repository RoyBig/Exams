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
@Document(collection = "closedquestion")
@NoArgsConstructor
public class Closedquestion {

    @Id
    private String id;

    @Field(name = "content")
    private String content;

    @Field(name = "score")
    private Integer score;

    @DBRef
    @Field(name = "exam")
    private Exam exam;

    public Closedquestion(String id, String content, Integer score, Exam exam) {
        this.id = id;
        this.content = content;
        this.score = score;
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "Closedquestion{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", exam=" + exam +
                '}';
    }
}