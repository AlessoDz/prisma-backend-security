package pe.edu.utp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.edu.utp.model.Administrator;
import pe.edu.utp.model.User;
import pe.edu.utp.service.AdministratorService;

import java.util.Date;

@SpringBootApplication
public class PrismaBackendApplication implements CommandLineRunner {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PrismaBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			// Crear un nuevo usuario
			User user = new User();
			user.setType("Administrator");
			user.setDni("12345678X");
			user.setName("Nombre");
			user.setLastname("Apellido");
			user.setEmail("correo@example.com");
			user.setPassword(bCryptPasswordEncoder.encode("123456"));
			user.setPhone("987654321");
			user.setAddress("Dirección");
			user.setBirthDate(new Date());
			user.setProfile("perfil.png");

			// Crear un nuevo administrador
			Administrator administrator = new Administrator();
			administrator.setUser(user);

			// Guardar el administrador utilizando el servicio correspondiente
			Administrator adminGuardado = administratorService.saveAdministrator(administrator);
			System.out.println("Administrador creado con ID: " + adminGuardado.getIdAdministrator());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
