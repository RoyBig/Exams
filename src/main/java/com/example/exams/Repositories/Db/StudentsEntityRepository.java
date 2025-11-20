package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentsEntityRepository {

    private final EntityManager em;

    @Autowired
    public StudentsEntityRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Student> findById(int id) {
        try {
            Student student = em.createQuery(
                            "SELECT s FROM Student s WHERE s.studentId = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(student);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Student findStudentByLogin(String login) {
        try {
            Student student = em.createQuery(
                            "SELECT s FROM Student s WHERE s.login = :login", Student.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return student;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Student findStudentByEmail(String email) {
        try {
            return em.createQuery(
                            "SELECT s FROM Student s WHERE s.email = :email", Student.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Student findStudentByStudentId(Integer studentId) {
        try {
            return em.createQuery(
                            "SELECT s FROM Student s WHERE s.studentId = :studentId", Student.class)
                    .setParameter("studentId", studentId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    public void save(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    public void delete(Student student) {
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
    }

    public void deleteById(int id) {
        findById(id).ifPresent(this::delete);
    }
}
