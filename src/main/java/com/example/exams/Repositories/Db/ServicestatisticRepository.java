package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Servicestatistic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicestatisticRepository extends MongoRepository<Servicestatistic, String> {

    Servicestatistic getServicestatisticById(String id);
}
