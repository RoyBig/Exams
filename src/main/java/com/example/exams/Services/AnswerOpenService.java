package com.example.exams.Services;

import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.LogstudentexamRepository;
import com.example.exams.Repositories.Db.StudentopenanswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnswerOpenService {
    private final StudentopenanswerRepository studentopenanswerRepository;
    private final LogstudentexamRepository logstudentexamRepository;

    @Autowired
    public AnswerOpenService(StudentopenanswerRepository studentopenanswerRepository,
                             LogstudentexamRepository logstudentexamRepository) {
        this.studentopenanswerRepository = studentopenanswerRepository;
        this.logstudentexamRepository = logstudentexamRepository;
    }

    public List<Student> getAllDistinctStudentsForOpenQuestions(String examId) {
        List<Studentopenanswer> studentopenanswers = studentopenanswerRepository.findAllByOpenquestionQuestionid_Exam_Id(examId);
        Set<Student> uniqueStudents = studentopenanswers.stream()
                .map(Studentopenanswer::getStudentStudent)
                .collect(Collectors.toSet());
        return sortByStudentId(List.copyOf(uniqueStudents));
    }

    public List<Student> sortByStudentId(List<Student> students) {
        List<Student> mutableList = new ArrayList<>(students);
        mutableList.sort(Comparator.comparing(Student::getStudentId));
        return mutableList;
    }

    public List<Studentopenanswer> getStudentOpenAnswerByStudent(Student student) {
        return studentopenanswerRepository.findAllByStudentStudent(student);
    }

    public int updateScores(Student student, List<Integer> scores, List<OpenQuestion> openQuestions) {
        int points = 0;
        if (scores.size() != openQuestions.size()) {
            throw new IllegalArgumentException("Scores size does not match questions size");
        }
        for (int i = 0; i < openQuestions.size(); i++) {
            OpenQuestion question = openQuestions.get(i);
            Integer score = scores.get(i);
            if (score == null) {
                score = 0; // Default to 0 if score is null (e.g., empty input from form)
            }
            Studentopenanswer answer = studentopenanswerRepository.findByStudentStudentAndOpenquestionQuestionid(student, question);
            if (answer == null) {
                answer = new Studentopenanswer();
                answer.setStudentStudent(student);
                answer.setOpenquestionQuestionid(question);
                answer.setDate(LocalDate.now());
                answer.setTime(LocalTime.now());
            }
            int previous = (answer.getScore() != null) ? answer.getScore() : 0;
            answer.setScore(score);
            points += score - previous;
            studentopenanswerRepository.save(answer);
        }
        return points;
    }
}