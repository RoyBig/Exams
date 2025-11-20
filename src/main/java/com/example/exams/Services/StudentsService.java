package com.example.exams.Services;

import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.StudentsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {

    @Autowired
    StudentsEntityRepository studentsRepository;

    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Student getStudentById(String studentId){
        return studentsRepository.findStudentByStudentId(studentId);
    }

    public Student getStudentByLoginAndEmail(String login, String email){
        return studentsRepository.findStudentByLoginAndEmail(login, email);
    }

    public List<Student> getStudentsByIds(List<String> studentIds) {
        List<Student> students = new ArrayList<>();

        for (String studentId : studentIds) {
            Student student = getStudentById(studentId);
            if (student != null) {
                students.add(student);
            }
        }

        return students;
    }

    public Student getStudentByLogin(String login) {
        return studentsRepository.findStudentByLogin(login);
    }

    public void editStudent(String studentId, String firstName, String lastName,
                            String login, String password, String email){
        Student student = studentsRepository.findStudentByStudentId(studentId);

        if(student != null){
            if(firstName != null && !firstName.trim().isEmpty())
                student.setFirstname(firstName);

            if(lastName != null && !lastName.trim().isEmpty())
                student.setLastname(lastName);

            if(login != null && !login.trim().isEmpty())
                student.setLogin(login);

            if(password != null && !password.trim().isEmpty())
                student.setPassword(password);

            if(email != null && !email.trim().isEmpty())
                student.setEmail(email);

            studentsRepository.save(student);
        }
    }

    public void deleteStudent(String id){
        studentsRepository.deleteById(id);
    }
}
