package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.model.Speciality;
import pe.edu.utp.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    int countBySpeciality(Speciality speciality);
}
