package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.implement.TeacherServiceImpl;
import pe.edu.utp.model.Teacher;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherServiceImpl.saveTeacher(teacher);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherServiceImpl.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Teacher teacher = teacherServiceImpl.getTeacherById(id);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher updatedTeacher) {
        Teacher updated = teacherServiceImpl.updateTeacher(id, updatedTeacher);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id) {
        teacherServiceImpl.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
