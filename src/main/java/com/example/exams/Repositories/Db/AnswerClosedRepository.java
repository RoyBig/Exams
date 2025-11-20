package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Answerclosed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnswerClosedRepository extends MongoRepository<Answerclosed, String> {

    List<Answerclosed> findByClosedquestionQuestionid_Id(String questionId);

    @Transactional
    void deleteByClosedquestionQuestionid_Id(String questionId);

    @Transactional
    void deleteById(String answerId);
}
