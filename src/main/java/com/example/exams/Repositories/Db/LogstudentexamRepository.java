package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Logstudentexam;
import com.example.exams.Model.Data.db.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogstudentexamRepository extends MongoRepository<Logstudentexam, String> {

    @Query("{ 'student_studentid.$id' : ?0 }")
    List<Logstudentexam> findByStudentStudentId(String studentId);

    Logstudentexam findLogstudentexamByStudentStudentAndExamExamid(Student studentStudent, Exam examExamid);

    List<Logstudentexam> findLogstudentexamsByExamExamid(Exam examExamid);

    void deleteByExamExamid(Exam exam);

    @Query(value = "{ 'exam_examid.$id' : ?0, 'student_studentid.$id' : ?1 }", exists = true)
    boolean existsByExamExamid_IdAndStudentStudent_Id(String examId, String studentId);
}
