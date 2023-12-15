package com.example.exams.Services;

import com.example.exams.Model.Data.ProperDataModels.ExamDTO;
import com.example.exams.Model.Data.ProperDataModels.OpenQuestionDTO;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.OpenQuestion;
import com.example.exams.Repositories.Db.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final SubjectService subjectService;
    private final ExaminerService egzaminatorService;
    private final OpenQuestionService openQuestionService;

    @Autowired
    public ExamService(ExamRepository examRepository, SubjectService subjectService, ExaminerService examinerService, OpenQuestionService openQuestionService) {
        this.examRepository = examRepository;
        this.subjectService = subjectService;
        this.egzaminatorService = examinerService;
        this.openQuestionService = openQuestionService;
    }

    public Exam AddExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam GetExam(int id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    public void updateExam(Exam updatedExam) {
        examRepository.save(updatedExam);
    }

    @Transactional
    public boolean deleteExam(Integer examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return true;
        }
        return false;
    }
    public Exam AddExam(ExamDTO examDTO){
        Exam exam = new Exam();
        exam.setExamsSubject(subjectService.GetOne(examDTO.getSubjectId()));
        exam.setConductingExaminer(egzaminatorService.Get(examDTO.getEgzamiantor()));
        exam.setDescription(examDTO.getDescription());
        exam.setStartDate(examDTO.getStartDate());
        exam.setStartTime(examDTO.getStartTime());
        exam.setEndDate(examDTO.getEndDate());
        exam.setEndTime(examDTO.getEndTime());
        Exam addedExam = examRepository.save(exam);
        for(OpenQuestionDTO openQuestionDTO : examDTO.getQuestions()){
            OpenQuestion openquestion = new OpenQuestion();
            openquestion.setExam(addedExam);
            openquestion.setScore(openQuestionDTO.getScore());
            openquestion.setContent(openQuestionDTO.getContent());
            openQuestionService.AddOpenQuestion(openquestion);
        }
        return addedExam;
    }
    public int getNextExamId(){
        return getAllExams().size()+1;
    }

    public String getExamChange(Exam currentExam, Exam changedExam)
    {
        String s="Zmiana nastąpiła w: ";
        if(!currentExam.getDescription().equals(changedExam.getDescription()) ){
            s+="opisie egzaminu - "+currentExam.getDescription()+" --> "+changedExam.getDescription();
        }
        if(!currentExam.getExamsSubject().equals(changedExam.getExamsSubject())){
            if(s != "Zmiana nastąpiła w: ") s+= ", ";
            s+="Temacie egzaminu - "+currentExam.getExamsSubject().getName()+" --> "+changedExam.getExamsSubject().getName();
        }

        if(currentExam.getStartDate() != null && changedExam.getStartDate() != null){
            if(!currentExam.getStartDate().equals(changedExam.getStartDate())){
                if(s != "Zmiana nastąpiła w: ") s+= ", ";
                s+="Start Date - "+currentExam.getStartDate()+" --> "+changedExam.getStartDate();

            }
        }
        else if(currentExam.getStartDate() == null && changedExam.getStartDate() != null){
            if(s != "Zmiana nastąpiła w: ") s+= ", ";
            s+="Start Date - "+currentExam.getStartDate()+" --> "+changedExam.getStartDate();
        }

        if(currentExam.getStartTime() != null && changedExam.getStartTime() != null){
            if(!currentExam.getStartTime().equals(changedExam.getStartTime())){
                if(s != "Zmiana nastąpiła w: ") s+= ", ";
                s+="Start Time - "+currentExam.getStartTime()+" --> "+changedExam.getStartTime();

            }
        }
        else if(currentExam.getStartTime() == null && changedExam.getStartTime() != null){
            if(s != "Zmiana nastąpiła w: ") s+= ", ";
            s+="Start Time - "+currentExam.getStartTime()+" --> "+changedExam.getStartTime();
        }

        if(currentExam.getEndDate() != null && changedExam.getEndDate() != null){
            if(!currentExam.getEndDate().equals(changedExam.getEndDate())){
                if(s != "Zmiana nastąpiła w: ") s+= ", ";
                s+="End Date - "+currentExam.getEndDate()+" --> "+changedExam.getEndDate();

            }
        }
        else if(currentExam.getEndDate() == null && changedExam.getEndDate() != null){
            if(s != "Zmiana nastąpiła w: ") s+= ", ";
            s+="End Date - "+currentExam.getEndDate()+" --> "+changedExam.getEndDate();
        }

        if(currentExam.getEndTime() != null && changedExam.getEndTime() != null){
            if(!currentExam.getEndTime().equals(changedExam.getEndTime())){
                if(s != "Zmiana nastąpiła w: ") s+= ", ";
                s+="End Time - "+currentExam.getEndTime()+" --> "+changedExam.getEndTime();

            }
        }
        else if(currentExam.getEndTime() == null && changedExam.getEndTime() != null){
            if(s != "Zmiana nastąpiła w: ") s+= ", ";
            s+="End Time - "+currentExam.getEndTime()+" --> "+changedExam.getEndTime();
        }

        if(s == "Zmiana nastąpiła w: ") return null;
        else return s;

    }




}
