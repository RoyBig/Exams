package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Student;
import com.example.exams.Model.Data.db.OpenQuestion;
import com.example.exams.Model.Data.db.Studentclosedanswer;
import com.example.exams.Model.Data.db.Studentopenanswer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Repository
public class StudentopenanswerRepository {
    private final EntityManager em;
    private final OpenQuestionRepository openQuestionRepository;

    public StudentopenanswerRepository(OpenQuestionRepository openQuestionRepository,
                                       EntityManager em) {
        this.openQuestionRepository = openQuestionRepository;
        this.em = em;
    }

    public List<Studentopenanswer> findAllByOpenquestionQuestionid_Exam_Id(int examId) {
        List<OpenQuestion> openQuestionList = openQuestionRepository.findByExamId(examId);
        Set<Integer> openQuestionIdList = openQuestionList.stream().
                map(OpenQuestion::getOpenQuestionId)
                .collect(Collectors.toSet());

        List<Studentopenanswer> studentopenanswerList = this.findAll();
        return studentopenanswerList.stream()
                .filter(e -> openQuestionIdList.contains(e.getOpenquestionQuestionid().getOpenQuestionId()))
                .toList();
    }

    public List<Studentopenanswer> findAll() {
        return em.createQuery("select a from Studentopenanswer a", Studentopenanswer.class)
                .getResultList();
    }

    public List<Studentopenanswer> findAllByStudentStudent(Student student) {
        return em.createQuery("select a from Studentopenanswer a", Studentopenanswer.class)
                .getResultList()
                .stream()
                .filter(e-> e.getStudentStudent().equals(student))
                .toList();
    }


    public List<Studentopenanswer> findByOpenquestionQuestionid(OpenQuestion openQuestion) {
        return em.createQuery(
                        "SELECT a FROM Studentopenanswer a WHERE a.openquestionQuestionid = :q",
                        Studentopenanswer.class
                )
                .setParameter("q", openQuestion)
                .getResultList();
    }

    public void deleteAll(List<Studentopenanswer> studentopenanswerList) {
        List<Integer> ids = studentopenanswerList.stream()
                .map(Studentopenanswer::getId)
                .toList();
        em.createQuery(
                "delete from Studentopenanswer a where a.id in :ids"
        ).setParameter("ids", ids).executeUpdate();
    }

    public Studentopenanswer save(Studentopenanswer studentopenanswer) {
        em.getTransaction().begin();
        em.persist(studentopenanswer);
        em.getTransaction().commit();
        return studentopenanswer;
    }
}