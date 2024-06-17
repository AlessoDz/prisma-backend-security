package pe.edu.utp.service;

import pe.edu.utp.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Student existingStudent, Student updatedStudent);

    void deleteStudent(Long id);

}
