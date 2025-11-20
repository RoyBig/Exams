package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Answerclosed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerClosedRepository {
    private final EntityManager em;

    @Autowired
    public AnswerClosedRepository(EntityManager em) {
        this.em = em;
    }

    public List<Answerclosed> findByClosedquestionQuestionid_Id(Integer questionId) {
        return em.createQuery("select a from Answerclosed a, Closedquestion q where a.closedquestionQuestionid = q and q.questionid = :questionId", Answerclosed.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }

    public void deleteByClosedquestionQuestionid_Id(Integer questionId) {
        em.createQuery("delete from Answerclosed a where a.closedquestionQuestionid = :questionId").executeUpdate();
    }

    public void deleteById(Integer answerId) {
        em.createQuery("delete from Answerclosed a where a.id = :answerId").executeUpdate();
    }

    public Answerclosed save(Answerclosed answerclosed) {
        em.getTransaction().begin();
        em.persist(answerclosed);
        em.getTransaction().commit();
        return answerclosed;
    }

    public Optional<Answerclosed> findById(Integer answerId) {
        return Optional.of(em.find(Answerclosed.class, answerId));
    }
}