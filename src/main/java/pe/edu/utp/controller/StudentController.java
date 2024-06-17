package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.implement.StudentServiceImpl;
import pe.edu.utp.model.Student;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentServiceImpl.saveStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentServiceImpl.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentServiceImpl.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student updatedStudent) {
        Student existingStudent = studentServiceImpl.getStudentById(id);

        if (existingStudent != null) {
            Student updated = studentServiceImpl.updateStudent(existingStudent, updatedStudent);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentServiceImpl.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
