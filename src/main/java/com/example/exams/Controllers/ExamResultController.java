package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Logstudentexam;
import com.example.exams.Services.ExamService;
import com.example.exams.Services.LogstudentexamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamResultController {

    private final LogstudentexamService logstudentexamService;
    private final ExamService examService;

    public ExamResultController(LogstudentexamService logstudentexamService, ExamService examService) {
        this.logstudentexamService = logstudentexamService;
        this.examService = examService;
    }

    @GetMapping("/exam/{examId}/grades")
    public ModelAndView showExamGrades(@PathVariable String examId) {
        Exam exam = examService.GetExam(examId);
        List<Logstudentexam> logs = logstudentexamService.getStudentsLogstudentExamById(exam);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("examResult");
        modelAndView.addObject("exam", exam);
        modelAndView.addObject("logs", logs);
        modelAndView.addObject("examId", examId);
        return modelAndView;
    }

    @GetMapping("/api/exam/{examId}/grades")
    @ResponseBody
    public Map<String, Integer> getExamGradesData(@PathVariable String examId) {
        Map<String, Integer> grades = new HashMap<>();
        grades.put("A",  logstudentexamService.getAGrades(examId));
        grades.put("B+", logstudentexamService.getBPlusGrades(examId));
        grades.put("B",  logstudentexamService.getBGrades(examId));
        grades.put("C+", logstudentexamService.getCPlusGrades(examId));
        grades.put("C",  logstudentexamService.getCGrades(examId));
        grades.put("D",  logstudentexamService.getDGrades(examId));
        return grades;
    }
}
