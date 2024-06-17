package pe.edu.utp.service;

import pe.edu.utp.model.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator saveAdministrator(Administrator administrator);

    List<Administrator> getAllAdministrators();

    Administrator getAdministratorById(Long id);

    Administrator updateAdministrator(Administrator existingAdministrator, Administrator updatedAdministrator);

    void deleteAdministrator(Long id);

}
