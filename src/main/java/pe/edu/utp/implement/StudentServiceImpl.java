package pe.edu.utp.implement;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.model.Student;
import pe.edu.utp.model.User;
import pe.edu.utp.repository.StudentRepository;
import pe.edu.utp.repository.UserRepository;
import pe.edu.utp.service.StudentService;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        User user = student.getUser();
        user.setType("Student");
        user = userRepository.save(user);
        student.setUser(user);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Student updateStudent(Student existingStudent, Student updatedStudent) {
        User existingUser = existingStudent.getUser();
        User updatedUser = updatedStudent.getUser();

        existingUser.setName(updatedUser.getName());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setProfile(updatedUser.getProfile());

        existingStudent.setGrade(updatedStudent.getGrade());
        existingStudent.setSection(updatedStudent.getSection());
        existingStudent.setStudyLevel(updatedStudent.getStudyLevel());
        existingStudent.setShift(updatedStudent.getShift());

        userRepository.save(existingUser);

        return studentRepository.save(existingStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            User user = student.getUser();
            studentRepository.deleteById(id); // Eliminar el estudiante
            if (user != null) {
                userRepository.delete(user); // Eliminar el usuario asociado
            }
        }
    }
}
