package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Closedquestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosedQuestionRepository extends MongoRepository<Closedquestion, String> {

    List<Closedquestion> findByExamId(String examId);
}
