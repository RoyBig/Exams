package com.example.exams.Model.Data.db;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Document(collection = "openquestion")
@NoArgsConstructor
public class OpenQuestion {

    @Id
    private String openQuestionId;

    @NotBlank(message = "Content cannot be empty")
    @Size(max = 100, message = "Content must not exceed 100 characters")
    @Field(name = "content")
    private String content;

    @NotNull(message = "Score cannot be empty")
    @Min(value = 0, message = "Score must be greater than or equal to 0")
    @Field(name = "score")
    private Integer score;

    @DBRef
    @Field(name = "exam")
    private Exam exam;

    public OpenQuestion(String openQuestionId, String content, Integer score, Exam exam) {
        this.openQuestionId = openQuestionId;
        this.content = content;
        this.score = score;
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "OpenQuestion{" +
                "openQuestionId='" + openQuestionId + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", exam=" + exam +
                '}';
    }
}