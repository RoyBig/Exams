package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.OpenQuestion;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Model.Data.db.Studentopenanswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentopenanswerRepository extends MongoRepository<Studentopenanswer, String> {

    List<Studentopenanswer> findAllByOpenquestionQuestionid_Exam_Id(String examId);

    List<Studentopenanswer> findAllByStudentStudent(Student student);

    List<Studentopenanswer> findByOpenquestionQuestionid(OpenQuestion openQuestion);
}
