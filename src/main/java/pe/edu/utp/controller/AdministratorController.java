package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.implement.AdministratorServiceImpl;
import pe.edu.utp.model.Administrator;
import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorServiceImpl administratorServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        Administrator newAdministrator = administratorServiceImpl.saveAdministrator(administrator);
        return new ResponseEntity<>(newAdministrator, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        List<Administrator> administrators = administratorServiceImpl.getAllAdministrators();
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable("id") Long id) {
        Administrator administrator = administratorServiceImpl.getAdministratorById(id);
        if (administrator != null) {
            return new ResponseEntity<>(administrator, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable("id") Long id, @RequestBody Administrator updatedAdministrator) {
        Administrator existingAdministrator = administratorServiceImpl.getAdministratorById(id);

        if (existingAdministrator != null) {
            Administrator updated = administratorServiceImpl.updateAdministrator(existingAdministrator, updatedAdministrator);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable("id") Long id) {
        administratorServiceImpl.deleteAdministrator(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}