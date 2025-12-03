package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends MongoRepository<Exam, String> {

    List<Exam> findByEndDateAfter(LocalDate date);

    List<Exam> findByVisibilityTrue();
    List<Exam> findByVisibilityFalse();

    List<Exam> findByStartDateBetween(LocalDate from, LocalDate to);
    List<Exam> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startFrom, LocalDate endTo);
    List<Exam> findByStartDateAfter(LocalDate date);
    List<Exam> findByEndDateBefore(LocalDate date);

    @Query("{ 'exams_subject.$id' : ?0 }")
    List<Exam> findByExamsSubjectId(String subjectId);

    @Query("{ 'conducting_examiner.$id' : ?0 }")
    List<Exam> findByConductingExaminerId(String examinerId);

    List<Exam> findByStudentsStudentId(String studentId);
    List<Exam> findByStudentsIn(List<com.example.exams.Model.Data.db.Student> students);

    List<Exam> findByDescriptionContainingIgnoreCase(String keyword);
    List<Exam> findByDescriptionRegex(String regex);

    List<Exam> findByQuestionPoolGreaterThanEqual(Integer minQuestions);
    List<Exam> findByQuestionPoolBetween(Integer min, Integer max);

    List<Exam> findByQuestionPoolStrategyTrue();
    List<Exam> findByQuestionPoolStrategyIsNull();

    List<Exam> findByDurationGreaterThan(Long minutes);
    List<Exam> findByDurationBetween(Long minMinutes, Long maxMinutes);

    List<Exam> findByVisibilityTrueAndStartDateAfter(LocalDate date);
    List<Exam> findByVisibilityTrueAndExamsSubjectId(String subjectId);

    @Query("{'start_date': {$lte: ?0}, 'end_date': {$gte: ?0}, 'visibility': true}")
    List<Exam> findCurrentlyActiveExams(LocalDate today);

    @Query("{'start_date': {$gte: ?0, $lte: ?1}, 'visibility': true}")
    List<Exam> findUpcomingExams(LocalDate from, LocalDate to);

    @Query("{'students.$id': ?0, 'visibility': true, 'end_date': {$gt: ?1}}")
    List<Exam> findVisibleFutureExamsForStudent(String studentId, LocalDate afterDate);

    @Query("{ $text: { $search: ?0 } }")
    List<Exam> findByTextSearch(String searchTerm);
}