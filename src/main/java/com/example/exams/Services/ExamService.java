package com.example.exams.Services;

import com.example.exams.Model.Data.ProperDataModels.ExamDTO;
import com.example.exams.Model.Data.ProperDataModels.OpenQuestionDTO;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.OpenQuestion;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.ExamRepository;
import com.example.exams.Repositories.Db.StudentsEntityRepository;
import com.example.exams.SpringSecurity.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final StudentsEntityRepository studentsRepository;
    private final SubjectService subjectService;
    private final ExaminerService egzaminatorService;
    private final OpenQuestionService openQuestionService;
    private final HttpSession httpSession;

    @Autowired
    public ExamService(ExamRepository examRepository, StudentsEntityRepository studentsRepository, SubjectService subjectService, ExaminerService examinerService, OpenQuestionService openQuestionService, HttpSession httpSession) {
        this.examRepository = examRepository;
        this.studentsRepository = studentsRepository;
        this.subjectService = subjectService;
        this.egzaminatorService = examinerService;
        this.openQuestionService = openQuestionService;
        this.httpSession = httpSession;
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
    public List<Exam> getUserExams() {
        CustomUserDetails loggedUser = (CustomUserDetails) httpSession.getAttribute("UserDetails");

        if (loggedUser != null) {
            Collection<? extends GrantedAuthority> authorities = loggedUser.getAuthorities();

            if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_EXAMINER"))) {
                return this.examRepository.findAll();
            } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"))) {
                Student student = studentsRepository.findById(loggedUser.getUserId()).orElse(null);
                if (student != null) {
                    return student.getExams();
                }
            }
        }

        return null;

    }

    public List<Exam> searchExams(String searchQuery) {

        List<Exam> allExams = getUserExams();

        List<Exam> matchingExams = allExams.stream()
                .filter(exam -> exam.getExamsSubject().getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        exam.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        return matchingExams;
    }
    public List<Exam> getExamsDependsOnDates(LocalDate startDate, LocalDate endDate) {
        List<Exam> allExams = getUserExams();
        List<Exam> examsBetweenDates;

        if (startDate != null && endDate != null) {
            examsBetweenDates = allExams.stream()
                    .filter(exam -> isDateAfter(exam.getStartDate(), startDate) &&
                            isDateBefore(exam.getStartDate(), endDate))
                    .collect(Collectors.toList());
        } else if (startDate != null) {
            examsBetweenDates = allExams.stream()
                    .filter(exam -> isDateAfter(exam.getStartDate(),startDate))
                    .collect(Collectors.toList());
        } else if (endDate != null) {
            examsBetweenDates = allExams.stream()
                    .filter(exam -> isDateBefore(exam.getStartDate(),endDate))
                    .collect(Collectors.toList());
        } else {
            examsBetweenDates = allExams;
        }

        return examsBetweenDates;
    }

    private boolean isDateAfter(LocalDate dateToCheck, LocalDate startDate) {
        return dateToCheck != null && dateToCheck.isAfter(startDate) || dateToCheck.isEqual(startDate);
    }

    private boolean isDateBefore(LocalDate dateToCheck, LocalDate endDate) {
        return dateToCheck != null && dateToCheck.isBefore(endDate) || dateToCheck.isEqual(endDate);
    }

    public List<Exam> getSortedExams(String sortType){
        List<Exam> exams = getUserExams();
        if ("Przedmiot A-Z".equals(sortType)) {
            exams.sort(Comparator.comparing(exam -> exam.getExamsSubject().getName()));
        } else if ("Przedmiot Z-A".equals(sortType)) {
            exams.sort(Comparator.comparing(exam -> exam.getExamsSubject().getName(), Comparator.reverseOrder()));
        } else if ("Data rosnąco".equals(sortType)) {
            exams.sort(Comparator.comparing(Exam::getStartDate));
        } else if ("Data malejąco".equals(sortType)) {
            exams.sort(Comparator.comparing(Exam::getStartDate, Comparator.reverseOrder()));
        }
        return exams;
    }

}
