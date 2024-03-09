package com.example.exams.Services;

import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.StudentsEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentsServiceTest {
    @Mock
    private StudentsEntityRepository studentsRepository;
    @InjectMocks
    private StudentsService studentsService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllStudentsTest() {
        Student student1 = new Student();
        Student student2 = new Student();

        when(studentsRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentsService.getAllStudents();

        assertNotNull(students);
        assertEquals(2, students.size());
        assertTrue(students.contains(student1) && students.contains(student2));
    }

    @Test
    void getStudentByIdTest() {
        Student student = new Student();
        Integer studentId = 1;

        when(studentsRepository.findStudentByStudentId(studentId)).thenReturn(student);

        Student found = studentsService.getStudentById(studentId);

        assertNotNull(found);
        assertEquals(student, found);
    }

    @Test
    void getStudentByLoginTest() {
        Student student = new Student();
        String login = "testLogin";

        when(studentsRepository.findStudentByLogin(login)).thenReturn(student);

        Student found = studentsService.getStudentByLogin(login);

        assertNotNull(found);
        assertEquals(student, found);
    }

    @Test
    void editStudentTest() {
        Student student = new Student(1, "John", "Doe", "johndoe", "password", "johndoe@example.com");
        when(studentsRepository.findStudentByStudentId(1)).thenReturn(student);
        doAnswer(invocation -> {
            Student s = invocation.getArgument(0);
            assertEquals(1, s.getStudentId());
            assertEquals("NewFirstName", s.getFirstname());
            assertEquals("NewLastName", s.getLastname());
            assertEquals("newlogin", s.getLogin());
            assertEquals("newpassword", s.getPassword());
            assertEquals("newemail@example.com", s.getEmail());
            return null;
        }).when(studentsRepository).save(any(Student.class));

        studentsService.editStudent(1, "NewFirstName", "NewLastName", "newlogin", "newpassword", "newemail@example.com");
        verify(studentsRepository, times(1)).save(student);
    }
}
