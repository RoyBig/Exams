package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentopenanswerRepository extends MongoRepository<Studentopenanswer, String> {

    List<Studentopenanswer> findAllByOpenquestionQuestionid_Exam_Id(String examId);

    List<Studentopenanswer> findAllByStudentStudent(Student student);

    List<Studentopenanswer> findByOpenquestionQuestionid(OpenQuestion openQuestion);

    @Query("{ 'student_student.$id' : ?0, 'openquestion_questionid.$id' : ?1 }")
    Studentopenanswer findByStudentStudentAndOpenquestionQuestionid(Student student, OpenQuestion openQuestion);
}