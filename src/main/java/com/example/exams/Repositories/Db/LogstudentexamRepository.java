package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Logstudentexam;
import com.example.exams.Model.Data.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class LogstudentexamRepository
{
    private final EntityManager em;

    @Autowired
    public LogstudentexamRepository(EntityManager em) {
        this.em = em;
    }

    public List<Logstudentexam> findByStudentStudentId(Integer studentId) {
        return em.createQuery("SELECT l FROM Logstudentexam l WHERE l.studentStudent.studentId = :studentId", Logstudentexam.class).setParameter("studentId", studentId).getResultList();
    }

    public Logstudentexam findLogstudentexamByStudentStudentAndExamExamid(Student studentStudent, Exam examExamid) {
        return em.createQuery("select Logstudentexam l from Logstudentexam where l.studentStudent = :studentStudent and l.examExamid = :examExamid", Logstudentexam.class)
                .setParameter("studentStudent", studentStudent)
                .setParameter("examExamid", examExamid).getSingleResult();
    }

    public List<Logstudentexam> findLogstudentexamsByExamExamid(Exam examExamid) {
        return em.createQuery("select Logstudentexam l from Logstudentexam where l.examExamid = :examExamid", Logstudentexam.class)
                .setParameter("examExamid", examExamid).getResultList();
    }

    public void deleteByExamExamid(Exam exam) {
        em.createQuery("delete from Logstudentexam l where l.id = :exam")
                .setParameter("exam", exam)
                .executeUpdate();
    }

    public boolean existsByExamExamid_IdAndStudentStudent_Id(Integer examId, Integer studentId) {
        return em.createQuery(
                        "SELECT COUNT(l) FROM Logstudentexam l " +
                                "WHERE l.studentStudent.studentId = :studentId " +
                                "AND l.examExamid.id = :examId",
                        Long.class
                )
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .getSingleResult() > 0;
    }

    public void save(Logstudentexam logstudentexam) {
        em.getTransaction().begin();
        em.persist(logstudentexam);
        em.getTransaction().commit();
    }

    public Optional<Logstudentexam> findById(Integer id) {
        return Optional.of(em.find(Logstudentexam.class, id));
    }
}
