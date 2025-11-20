package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GroupEntityRepository {
    private final EntityManager em;

    @Autowired
    public GroupEntityRepository(EntityManager em) {
        this.em = em;
    }

    public Group findGroupById(Integer groupId) {
        return em.find(Group.class, groupId);
    }

    public List<Group> findAll() {
        return em.createQuery("select g from Group g", Group.class).getResultList();
    }

    public void save(Group group) {
        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();
    }

    public Group findById(Integer groupId) {
        return em.find(Group.class, groupId);
    }

    public boolean existsById(Integer id) {
        return em.find(Group.class, id) != null;
    }

    public void deleteById(Integer id) {
        em.createQuery("delete from Group g where g.id = :id").setParameter("id", id).executeUpdate();
    }
}
