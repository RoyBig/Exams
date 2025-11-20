package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProblemRepository {
    private final EntityManager em;

    @Autowired
    public ProblemRepository(EntityManager em) {
        this.em = em;
    }

    public Problem save(Problem problem) {
        em.getTransaction().begin();
        em.persist(problem);
        em.getTransaction().commit();
        return problem;
    }

    public List<Problem> findAll() {
        return em.createQuery("select p from Problem p", Problem.class).getResultList();
    }

    public Problem getReferenceById(int id) {
        return em.find(Problem.class, id);
    }
}
