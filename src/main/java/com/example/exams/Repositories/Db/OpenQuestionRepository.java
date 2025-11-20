package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.OpenQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class OpenQuestionRepository {
    private EntityManager em;

    @Autowired
    public OpenQuestionRepository(EntityManager em) {
        this.em = em;
    }

    public OpenQuestion save(OpenQuestion question) {
        return em.merge(question);
    }

    public Optional<OpenQuestion> findById(int id) {
        try {
            OpenQuestion question = em.createQuery(
                            "SELECT q FROM OpenQuestion q WHERE q.openQuestionId = :id",
                            OpenQuestion.class
                    )
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(question);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<OpenQuestion> findAll() {
        return em.createQuery("SELECT q FROM OpenQuestion q", OpenQuestion.class)
                .getResultList();
    }

    public List<OpenQuestion> findByExamId(Integer examId) {
        return em.createQuery(
                        "SELECT q FROM OpenQuestion q WHERE q.exam.id = :examId",
                        OpenQuestion.class
                )
                .setParameter("examId", examId)
                .getResultList();
    }

    public boolean existsById(Integer id) {
        Long count = em.createQuery(
                        "SELECT COUNT(q) FROM OpenQuestion q WHERE q.openQuestionId = :id",
                        Long.class
                )
                .setParameter("id", id)
                .getSingleResult();

        return count > 0;
    }

    public void deleteById(Integer id) {
        OpenQuestion q = em.find(OpenQuestion.class, id);
        if (q != null) {
            em.remove(q);
        }
    }
}
