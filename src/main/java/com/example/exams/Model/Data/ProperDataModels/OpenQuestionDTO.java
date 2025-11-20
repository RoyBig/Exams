package com.example.exams.Model.Data.ProperDataModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenQuestionDTO {

    private String questionid;

    @JsonProperty("content")
    private String content;

    @JsonProperty("score")
    private Integer score;

    private String subjectId;
    private String examId;
}
