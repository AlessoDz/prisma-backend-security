package pe.edu.utp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Speciality")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSpeciality;

    private String name;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Teacher> teachers;

    //Getters & Setters
    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
