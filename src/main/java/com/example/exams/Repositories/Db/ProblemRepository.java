package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Problem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends MongoRepository<Problem, String> {

}
