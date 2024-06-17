package pe.edu.utp.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.model.Administrator;
import pe.edu.utp.model.User;
import pe.edu.utp.repository.AdministratorRepository;
import pe.edu.utp.repository.UserRepository;
import pe.edu.utp.service.AdministratorService;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Administrator saveAdministrator(Administrator administrator) {
        User user = administrator.getUser();
        user.setType("Administrator");
        user = userRepository.save(user);
        administrator.setUser(user);
        return administratorRepository.save(administrator);
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        return administratorRepository.findById(id).orElse(null);
    }

    @Override
    public Administrator updateAdministrator(Administrator existingAdministrator, Administrator updatedAdministrator) {
        User existingUser = existingAdministrator.getUser();
        User updatedUser = updatedAdministrator.getUser();

        existingUser.setName(updatedUser.getName());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setProfile(updatedUser.getProfile());

        User savedUser = userRepository.save(existingUser);

        return administratorRepository.save(existingAdministrator);
    }

    @Override
    public void deleteAdministrator(Long id) {
        Administrator administrator = administratorRepository.findById(id).orElse(null);
        if (administrator != null) {
            User user = administrator.getUser();
            administratorRepository.delete(administrator);
            if (user != null) {
                userRepository.delete(user);
            }
        }
    }
}
