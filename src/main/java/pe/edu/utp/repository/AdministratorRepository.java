package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
