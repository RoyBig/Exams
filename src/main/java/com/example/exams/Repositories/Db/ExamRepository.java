package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}