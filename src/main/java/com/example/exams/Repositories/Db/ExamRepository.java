package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;

import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ExamRepository {

    private final EntityManager em;

    public ExamRepository(EntityManager em) {
        this.em = em;
    }

    public List<Exam> findAll() {
        return em.createQuery("SELECT e FROM Exam e", Exam.class)
                .getResultList();
    }

    public Optional<Exam> findById(int id) {
        try {
            Exam exam = em.createQuery("SELECT e FROM Exam e WHERE e.id = :id", Exam.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(exam);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Exam save(Exam exam) {
        em.getTransaction().begin();
        Exam merged = em.merge(exam);
        em.getTransaction().commit();
        return merged;
    }

    public void delete(Exam exam) {
        em.getTransaction().begin();
        if (!em.contains(exam)) {
            exam = em.merge(exam);
        }
        em.remove(exam);
        em.getTransaction().commit();
    }

    public List<Exam> findByEndDateAfter(LocalDate date) {
        return em.createQuery("SELECT e FROM Exam e WHERE e.endDate > :date", Exam.class)
                .setParameter("date", date)
                .getResultList();
    }


    public Exam getReferenceById(int id) {
        return em.getReference(Exam.class, id);
    }
}
