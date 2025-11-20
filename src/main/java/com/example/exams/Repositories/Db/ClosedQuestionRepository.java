package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Closedquestion;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClosedQuestionRepository {
    private final EntityManager entityManager;

    @Autowired
    public ClosedQuestionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Closedquestion> findByExamId(Integer examId) {
        return entityManager.
                createQuery("select q from Closedquestion q where q.exam = :examId", Closedquestion.class).
                setParameter("examId", examId)
                .getResultList();
    }

    public void deleteById(Integer id) {
        entityManager.createQuery("delete Closedquestion q where q.id = :id").setParameter("id", id).executeUpdate();
    }

    public Optional<Closedquestion> findById(Integer id) {
        return Optional.of(entityManager.createQuery("select q from Closedquestion q where q.id = :id", Closedquestion.class).setParameter("id", id).getSingleResult());
    }

    public Closedquestion save(Closedquestion closedquestion) {
        entityManager.getTransaction().begin();
        entityManager.persist(closedquestion);
        entityManager.getTransaction().commit();
        return closedquestion;
    }
}