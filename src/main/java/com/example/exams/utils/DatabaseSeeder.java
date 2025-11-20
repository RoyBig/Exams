package com.example.exams.utils;

import com.example.exams.Model.Data.ProperDataModels.ProblemCategories;
import com.example.exams.Model.Data.ProperDataModels.ProblemStatus;
import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.*;
import com.example.exams.Services.GroupsService;
import com.example.exams.Services.LogstudentexamService;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AdministratorsEntityRepository administratorRepository;
    private final SubjectRepository subjectRepository;
    private final ExaminerRepository examinerRepository;
    private final ExamRepository examRepository;
    private final OpenQuestionRepository openQuestionRepository;
    private final ClosedQuestionRepository closedQuestionRepository;
    private final AnswerClosedRepository answerClosedRepository;

    private final StudentsEntityRepository studentsRepository;
    private final ProblemRepository problemRepository;
    private final GroupEntityRepository groupRepository;
    private final ServicestatisticRepository servicestatisticRepository;
    private final LogstudentexamRepository logstudentexamRepository;
    private final LogstudentexamService logstudentexamService;
    private final EntityManager em;

    @Autowired
    public DatabaseSeeder(AdministratorsEntityRepository administratorRepository, ExamRepository examRepository, SubjectRepository subjectRepository, ExaminerRepository examinersRepository, OpenQuestionRepository openQuestionRepository, ClosedQuestionRepository closedQuestionRepository, AnswerClosedRepository answerClosedRepository, StudentsEntityRepository studentsEntityRepository, ProblemRepository problemRepository, GroupEntityRepository groupRepository, ServicestatisticRepository servicestatisticRepository, LogstudentexamRepository logstudentexamRepository, LogstudentexamService logstudentexamService, EntityManager em) {
        this.administratorRepository = administratorRepository;
        this.examinerRepository = examinersRepository;
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.closedQuestionRepository = closedQuestionRepository;
        this.answerClosedRepository = answerClosedRepository;
        this.studentsRepository = studentsEntityRepository;
        this.problemRepository = problemRepository;
        this.groupRepository = groupRepository;
        this.servicestatisticRepository = servicestatisticRepository;
        this.logstudentexamRepository = logstudentexamRepository;
        this.logstudentexamService = logstudentexamService;
        this.em = em;
    }

    @Override
    public void run(String... args) throws Exception {
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Exam").executeUpdate();
        em.createQuery("DELETE FROM Student").executeUpdate();
        em.createQuery("DELETE FROM Examiner").executeUpdate();
        em.createQuery("DELETE FROM Subject").executeUpdate();

        em.getTransaction().commit();

        Servicestatistic servicestatistic = new Servicestatistic();
        servicestatisticRepository.save(servicestatistic);

        Subject math = new Subject(1, "Matematyka");
        Subject english = new Subject(2, "Angielski");
        subjectRepository.save(math);
        subjectRepository.save(english);

        Examiner examiner1 = new Examiner(1, "Slawomir", "Golibroda", "z", "z", "s.golibrodai@pb.edu.pl", true);
        Examiner examiner2 = new Examiner(2, "Dorota", "Warka", "warka", "warka", "wedliny@pb.edu.pl", true);
        examinerRepository.save(examiner1);
        examinerRepository.save(examiner2);


        Student student1 = new Student(1, "Anna", "Mosiezna", "a", "a", "a@student.pb.edu.pl");
        Student student2 = new Student(2, "Bartosz", "Balagan", "balagan", "balagan", "b@student.pb.edu.pl");
        studentsRepository.save(student1);
        studentsRepository.save(student2);

        Exam exam1 = new Exam(1, 6, false, "Projektowanie części w SOLIDWORKS",
                LocalDate.now(), LocalTime.now().withNano(0),
                LocalDate.now(), LocalTime.now().plusHours(2).withNano(0),
                null, new ArrayList<>());
        exam1.setConductingExaminer(examiner1);
        exam1.setVisibility(true);

        Exam exam2 = new Exam(2, 0, false, "Retusz zdjęć",
                LocalDate.now().minusDays(2), LocalTime.now().minusHours(4).withNano(0),
                LocalDate.now(), LocalTime.now().plusHours(1).withNano(0),
                null, new ArrayList<>());

        student1.setExams(new ArrayList<>(){{ add(exam1); add(exam2); }});
        student2.setExams(new ArrayList<>(){{ add(exam1); }});

        exam1.getStudents().add(student1);
        exam1.getStudents().add(student2);
        exam2.getStudents().add(student1);

        examRepository.save(exam1);
        examRepository.save(exam2);

        studentsRepository.save(student1);
        studentsRepository.save(student2);
    }

    public static byte[] convertImage(String resourcePath) throws Exception {
        ClassPathResource imgFile = new ClassPathResource(resourcePath);
        BufferedImage bImage = ImageIO.read(imgFile.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}

