package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Administrator;
import com.example.exams.Model.Data.db.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

@Repository
public class AdministratorsEntityRepository {

    private final EntityManager em;

    public AdministratorsEntityRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Administrator> findById(int id) {
        try {
            Administrator admin = em.createQuery(
                            "SELECT a FROM Administrator a WHERE a.id = :id", Administrator.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    public Administrator findAdministratorByLogin(String login) {
        try {
            return em.createQuery(
                            "SELECT a FROM Administrator a WHERE a.login = :login", Administrator.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Administrator findAdministratorByEmail(String email) {
        try {
            return em.createQuery(
                            "SELECT a FROM Administrator a WHERE a.email = :email", Administrator.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Administrator findAdministratorById(Integer id) {
        try {
            return em.createQuery(
                            "SELECT a FROM Administrator a WHERE a.id = :id", Administrator.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Administrator> findAll() {
        return em.createQuery("SELECT a FROM Administrator a", Administrator.class)
                .getResultList();
    }

    public void save(Administrator administrator) {
        em.getTransaction().begin();
        em. persist(administrator);
        em.getTransaction().commit();
    }

    public void delete(Administrator administrator) {
        em.getTransaction().begin();
        em.remove(administrator);
        em.getTransaction().commit();
    }

    public void deleteById(Integer id) {
        Administrator a = findAdministratorById(id);
        if (a != null) {
            delete(a);
        }
    }
}
