package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEntityRepository extends MongoRepository<Group, String> {

    Group findGroupById(String groupId);
}
