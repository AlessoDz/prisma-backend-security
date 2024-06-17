package pe.edu.utp.service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher saveTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    @Transactional
    Teacher updateTeacher(Long id, Teacher updatedTeacher);

    void deleteTeacher(Long id);

}
