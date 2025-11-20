package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Servicestatistic;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServicestatisticRepository {
    private final EntityManager em;

    @Autowired
    public ServicestatisticRepository(EntityManager em) {
        this.em = em;
    }

    public Servicestatistic getServicestatisticById(int id) {
        return em.find(Servicestatistic.class, id);
    }

    public void save(Servicestatistic servicestatistic) {
        em.getTransaction().begin();
        em.persist(servicestatistic);
        em.getTransaction().commit();
    }

}