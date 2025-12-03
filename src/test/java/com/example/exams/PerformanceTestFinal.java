package com.example.exams;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Subject;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.ExamRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.model.Indexes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PerformanceTestFinal {

    @Autowired private ExamRepository examRepository;
    @Autowired private MongoTemplate mongoTemplate;

    private final Faker faker = new Faker();
    private final LocalDate TODAY = LocalDate.now();
    private final int[] SIZES = {1, 100, 2000, 4000, 8000, 20000};

    @Test
    @Order(1)
    public void testWithIndexes() {
        System.out.println("\n=== Z INDEKSAMI ===\n");
        runTests(true);
    }

    @Test
    @Order(2)
    public void testWithoutIndexes() {
        mongoTemplate.getCollection("exam").dropIndexes();
        System.out.println("\n=== BEZ INDEKSÓW ===\n");
        runTests(false);
    }

    private void runTests(boolean withIndexes) {
        for (int size : SIZES) {
            clearCollection();
            insertTestData(size);

            if (withIndexes) {
                mongoTemplate.getCollection("exam").createIndex(Indexes.text("description"));
            }

            System.out.println("\n--- " + String.format("%,7d", size) + " rekordów " + (withIndexes ? "[z indeksami]" : "[bez indeksów]") + " ---");

            measure("insert jednego (save)            ", () -> examRepository.save(generateExam()));
            measure("update jednego                   ", () -> updateFirst());
            measure("delete + insert back             ", () -> deleteAndInsertBack());
            measure("findCurrentlyActiveExams()       ", () -> examRepository.findCurrentlyActiveExams(TODAY));
            measure("findUpcomingExams()              ", () -> examRepository.findUpcomingExams(TODAY, TODAY.plusDays(90)));
            measure("findByExamsSubjectId()           ", () -> examRepository.findByExamsSubjectId("subject123"));
            measure("findByVisibilityTrue()           ", () -> examRepository.findByVisibilityTrue());
            measure("findVisibleFutureExamsForStudent ", () -> examRepository.findVisibleFutureExamsForStudent("student123", TODAY));

            if (withIndexes) {
                measure("findByTextSearch(\"java\")         ", () -> examRepository.findByTextSearch("java"));
            } else {
                measure("text search (containingIgnoreCase)", () -> examRepository.findByDescriptionContainingIgnoreCase("java"));
            }
        }
    }

    private void measure(String name, Runnable task) {
        long total = 0;
        int runs = 10;
        task.run();

        for (int i = 0; i < runs; i++) {
            long start = System.nanoTime();
            task.run();
            total += System.nanoTime() - start;
        }
        double avgMs = total / runs / 1_000_000.0;
        System.out.printf("%-40s → %8.3f ms%n", name, avgMs);
    }

    private void clearCollection() {
        examRepository.deleteAll();
    }

    private void insertTestData(int count) {
        List<Exam> exams = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            exams.add(generateExam());
        }
        examRepository.saveAll(exams);
    }

    private Exam generateExam() {
        Exam exam = new Exam();
        exam.setDescription(faker.lorem().sentence(10, 15) + " java spring mongodb performance test exam");
        exam.setStartDate(TODAY.plusDays(faker.number().numberBetween(-600, 600)));
        exam.setEndDate(exam.getStartDate().plusDays(faker.number().numberBetween(1, 60)));
        exam.setStartTime(LocalTime.of(faker.number().numberBetween(8, 19), 0));
        exam.setEndTime(exam.getStartTime().plusHours(faker.number().numberBetween(1, 6)));
        exam.setVisibility(faker.bool().bool());
        exam.setQuestionPool(faker.number().numberBetween(10, 300));
        exam.setDuration(180L);

        Subject subject = new Subject();
        subject.setId("subject123");
        exam.setExamsSubject(subject);

        if (faker.bool().bool()) {
            Student student = new Student();
            student.setStudentId("student123");
            exam.setStudents(List.of(student));
        }

        return exam;
    }

    private void updateFirst() {
        Exam exam = examRepository.findAll().get(0);
        exam.setDescription("updated " + System.nanoTime());
        examRepository.save(exam);
    }

    private void deleteAndInsertBack() {
        Exam exam = examRepository.findAll().get(0);
        examRepository.deleteById(exam.getId());
        examRepository.save(generateExam());
    }
}