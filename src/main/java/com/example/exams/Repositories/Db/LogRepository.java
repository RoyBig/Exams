package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LogRepository {
    private final EntityManager em;

    public LogRepository(EntityManager em) {
        this.em = em;
    }

    public List<Log> findAll() {
        return em.createQuery("SELECT l FROM Log l", Log.class).getResultList();
    }

    public void save(Log log) {
        em.getTransaction().begin();
        em.persist(log);
        em.getTransaction().commit();
    }
}