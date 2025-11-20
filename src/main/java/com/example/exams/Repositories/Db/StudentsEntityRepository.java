package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsEntityRepository extends MongoRepository<Student, String> {

    Student findStudentByLogin(String login);

    Student findStudentByLoginAndEmail(String login, String email);

    List<Student> findAll();

    Student findStudentByStudentId(String studentId);
}
