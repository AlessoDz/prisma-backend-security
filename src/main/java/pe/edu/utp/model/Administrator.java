package pe.edu.utp.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrator;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    // Getters & setters

    public Long getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(Long idAdministrator) {
        this.idAdministrator = idAdministrator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
