package pe.edu.utp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    private int grade;
    private String section;
    private int studyLevel;
    private String shift;


    // Getters & setters

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(int studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
