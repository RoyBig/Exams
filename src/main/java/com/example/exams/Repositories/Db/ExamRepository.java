package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends MongoRepository<Exam, String> {

    List<Exam> findByEndDateAfter(LocalDate date);
}
