package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Examiner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminerRepository extends MongoRepository<Examiner, String> {

    Examiner findExaminerByLogin(String login);

    Examiner findByLogin(String login);

    Examiner findExaminerByLoginAndEmail(String login, String email);

    @Query("{ 'examiner_id': ?0 }")
    Examiner findExaminerById(String id);
}
