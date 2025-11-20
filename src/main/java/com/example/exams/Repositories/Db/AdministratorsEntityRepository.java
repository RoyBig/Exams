package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Administrator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AdministratorsEntityRepository extends MongoRepository<Administrator, String> {
    @Query("{ 'login' : ?0 }")
    Administrator findAdministratorByLogin(String login);
    @Query("{ 'login' : ?0, 'email' : ?1 }")
    Administrator findAdministratorByLoginAndEmail(String login, String email);
    @Query("{ 'administratorId' : ?0 }")
    Administrator findAdministratorById(String id);
}