package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
