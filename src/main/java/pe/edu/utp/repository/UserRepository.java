package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
