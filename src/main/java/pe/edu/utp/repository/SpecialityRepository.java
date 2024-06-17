package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Speciality findByName(String name);
}
