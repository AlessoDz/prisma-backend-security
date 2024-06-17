package pe.edu.utp.implement;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.model.Speciality;
import pe.edu.utp.model.Teacher;
import pe.edu.utp.model.User;
import pe.edu.utp.repository.SpecialityRepository;
import pe.edu.utp.repository.TeacherRepository;
import pe.edu.utp.repository.UserRepository;
import pe.edu.utp.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    @Transactional
    public Teacher saveTeacher(Teacher teacher) {
        User user = teacher.getUser();
        user.setType("Teacher");
        user = userRepository.save(user);
        teacher.setUser(user);

        Speciality speciality = teacher.getSpeciality();
        if (speciality != null) {
            Speciality existingSpeciality = specialityRepository.findByName(speciality.getName());
            if (existingSpeciality != null) {
                teacher.setSpeciality(existingSpeciality);
            } else {
                speciality = specialityRepository.save(speciality);
                teacher.setSpeciality(speciality);
            }
        }

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);
        if (existingTeacher == null) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }

        User existingUser = existingTeacher.getUser();
        User updatedUser = updatedTeacher.getUser();
        existingUser.setName(updatedUser.getName());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setProfile(updatedUser.getProfile());
        userRepository.save(existingUser);

        Speciality updatedSpeciality = updatedTeacher.getSpeciality();
        if (updatedSpeciality != null) {
            Speciality existingSpeciality = existingTeacher.getSpeciality();
            if (existingSpeciality != null && !existingSpeciality.getName().equals(updatedSpeciality.getName())) {
                existingSpeciality.setName(updatedSpeciality.getName());
                specialityRepository.save(existingSpeciality);
                existingTeacher.setSpeciality(existingSpeciality);
            } else if (existingSpeciality == null) {
                Speciality newSpeciality = specialityRepository.findByName(updatedSpeciality.getName());
                if (newSpeciality == null) {
                    newSpeciality = specialityRepository.save(updatedSpeciality);
                }
                existingTeacher.setSpeciality(newSpeciality);
            }
        } else {
            existingTeacher.setSpeciality(null);
        }

        return teacherRepository.save(existingTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            User user = teacher.getUser();
            Speciality speciality = teacher.getSpeciality();
            teacherRepository.deleteById(id);
            if (user != null) {
                userRepository.delete(user);
            }
            if (speciality != null) {
                long count = teacherRepository.countBySpeciality(speciality);
                if (count == 0) {
                    specialityRepository.delete(speciality);
                }
            }
        }
    }
}
