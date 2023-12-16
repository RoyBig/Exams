package com.example.exams.Services;

import com.example.exams.Model.Data.db.Closedquestion;
import com.example.exams.Repositories.Db.ClosedQuestionRepository;
import com.example.exams.Repositories.Db.StudentclosedanswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosedQuestionService {
    private final ClosedQuestionRepository closedQuestionRepository;
    private final StudentclosedanswerRepository studentclosedanswerRepository;
    private final StudentClosedAnswerService studentClosedAnswerService;
    private final AnswerClosedService answerClosedService;

    @Autowired
    public ClosedQuestionService(ClosedQuestionRepository closedQuestionRepository,
                                 StudentclosedanswerRepository studentclosedanswerRepository, StudentClosedAnswerService studentClosedAnswerService,
                                 AnswerClosedService answerClosedService){
        this.closedQuestionRepository = closedQuestionRepository;
        this.studentclosedanswerRepository = studentclosedanswerRepository;
        this.studentClosedAnswerService = studentClosedAnswerService;
        this.answerClosedService = answerClosedService;
    }
    public List<Closedquestion> getAllByExamId(int examId) {
        return closedQuestionRepository.findByExamId(examId);
    }

    public Closedquestion addClosedQuestion(Closedquestion newClosedQuestion)
    {
        return closedQuestionRepository.save(newClosedQuestion);
    }

    public Closedquestion getClosedQuestionById(Integer questionId) {
        return closedQuestionRepository.findById(questionId).orElse(null);
    }

    public void deleteClosedQuestion(Integer questionId) {
        closedQuestionRepository.deleteById(questionId);
    }

    public void deleteAllClosedQuestionsByExamId(int examId) {
        List<Closedquestion> questions = closedQuestionRepository.findByExamId(examId);
        for (Closedquestion question : questions) {

            studentClosedAnswerService.deleteStudentClosedAnswersByQuestionId(question.getId());
            answerClosedService.deleteAnswersByQuestionId(question.getId());
            closedQuestionRepository.deleteById(question.getId());
        }
    }
}