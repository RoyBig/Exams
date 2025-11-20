package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectRepository {
    private final EntityManager em;

    public SubjectRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    public Optional<Subject> findById(int id) {
        try {
            Subject subject = em.createQuery("select s from Subject s where s.id = :id", Subject.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(subject);
        } catch(NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Subject> findAll() {
        return em.createQuery("select s from Subject s", Subject.class).getResultList();
    }

    public void save(Subject subject) {
        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
    }
}
