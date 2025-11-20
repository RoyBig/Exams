package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Studentclosedanswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentclosedanswerRepository extends MongoRepository<Studentclosedanswer, String> {

    List<Studentclosedanswer> findAllByAnswerclosedAnswerid_ClosedquestionQuestionid_Exam_Id(String examId);

    List<Studentclosedanswer> findAllByAnswerclosedAnswerid_ClosedquestionQuestionid_Exam_Id_AndStudentStudent_StudentId(
            String examId,
            String studentId
    );

    @Transactional
    @Query(value = "{ 'closedquestion_questionid.$id' : ?0 }", delete = true)
    void deleteByQuestionId(String questionId);

    @Transactional
    @Query(value = "{ '_id' : ?0 }", delete = true)
    void deleteByAnswerId(String answerId);
}
