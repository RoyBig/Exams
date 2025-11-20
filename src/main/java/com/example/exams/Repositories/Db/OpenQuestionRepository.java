package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.OpenQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenQuestionRepository extends MongoRepository<OpenQuestion, String> {

    @Query("{ 'exam.$id' : ?0 }")
    List<OpenQuestion> findByExamId(String examId);
}
