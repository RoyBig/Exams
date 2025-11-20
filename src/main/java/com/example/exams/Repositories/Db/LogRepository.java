package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {

}