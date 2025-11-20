package com.example.exams.Model.Data.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "openquestion")
public class OpenQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_question_id", nullable = false)
    private Integer openQuestionId;

    //@NotBlank(message = "Content cannot be empty")
    //@Size(max = 100, message = "Content must not exceed 100 characters")
    @Column(name = "content", length = 100, nullable = false)
    private String content;

    //@NotNull(message = "Score cannot be empty")
    //@Min(value = 0, message = "Score must be greater than or equal to 0")
    @Column(name = "score", nullable = false)
    private Integer score;

    @Override
    public String toString() {
        return "OpenQuestion{" +
                "openQuestionId=" + openQuestionId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", exam=" + exam +
                '}';
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public OpenQuestion(Integer openQuestionId, String content, Integer score, Exam exam) {
        this.openQuestionId = openQuestionId;
        this.content = content;
        this.score = score;
        this.exam = exam;
    }
}
