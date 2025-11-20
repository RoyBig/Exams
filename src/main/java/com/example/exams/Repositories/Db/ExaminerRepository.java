package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Model.Data.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class ExaminerRepository {
    private final EntityManager em;

    @Autowired
    public ExaminerRepository(EntityManager em) {
        this.em = em;
    }

    public Examiner findExaminerByLogin(String login) {
        try {
            return em.createQuery("select e from Examiner e where e.login = :login", Examiner.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Examiner findByLogin(String login) {
        try {
            return em.createQuery("select e from Examiner e where e.login = :login", Examiner.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Examiner findExaminerByEmail(String email) {
        try {
            return em.createQuery("select e from Examiner e where e.email = :email", Examiner.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Examiner findByEmail(String email) {
        try {
            return em.createQuery("select e from Examiner e where e.email = :email", Examiner.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Optional<Examiner> findById(int id) {
        try {
            Examiner examiner = em.createQuery(
                            "SELECT e FROM Examiner e WHERE e.id = :id", Examiner.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(examiner);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Examiner findExaminerById(int id) {
        try {
            return em.createQuery(
                            "SELECT e FROM Examiner e WHERE e.id = :id", Examiner.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deleteById(int id) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Examiner e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public List<Examiner> findAll() {
        return em.createQuery("SELECT e FROM Examiner e", Examiner.class)
                .getResultList();
    }

    public void save(Examiner examiner) {
        em.getTransaction().begin();
        em.persist(examiner);
        em.getTransaction().commit();
    }

    public void update(Examiner examiner) {
        em.getTransaction().begin();
        em.merge(examiner);
        em.getTransaction().commit();
    }

    public void delete(Examiner examiner) {
        em.getTransaction().begin();
        em.remove(examiner);
        em.getTransaction().commit();
    }
}
