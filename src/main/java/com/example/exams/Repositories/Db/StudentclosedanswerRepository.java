package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Closedquestion;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Model.Data.db.Studentclosedanswer;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentclosedanswerRepository {
    private final EntityManager em;

    @Autowired
    public StudentclosedanswerRepository(EntityManager em) {
        this.em = em;
    }

    public List<Studentclosedanswer> findAllByAnswerclosedAnswerid_ClosedquestionQuestionid_Exam_Id(int examId) {
         return em.createQuery("select a from Studentclosedanswer a, Closedquestion q where a.closedquestionQuestionid = q and q.exam = :examId", Studentclosedanswer.class).setParameter("examId", examId).getResultList();
    }

    public List<Studentclosedanswer> findAllByAnswerclosedAnswerid_ClosedquestionQuestionid_Exam_Id_AndStudentStudent_StudentId(int examId, int studentId) {
        return em.createQuery("select a from Studentclosedanswer a, Closedquestion q where a.closedquestionQuestionid = q and q.exam = :examId and a.studentStudent = :studentId", Studentclosedanswer.class)
                .setParameter("examId", examId).
                setParameter("studentId", studentId).
                getResultList();
    }

    public void deleteByQuestionId(@Param("questionId") int questionId) {
        em.createQuery("delete from Studentclosedanswer s where s.answerclosedAnswerid.closedquestionQuestionid.id = :questionId").executeUpdate();
    }

    public void deleteByAnswerId(@Param("answerId") int answerId) {
        em.createQuery("delete from Studentclosedanswer s where s.id = :answerId").setParameter("answerId", answerId).executeUpdate();
    }

    public void save(Studentclosedanswer closedAnswer) {
        em.getTransaction().begin();
        em.persist(closedAnswer);
        em.getTransaction().commit();
    }
}