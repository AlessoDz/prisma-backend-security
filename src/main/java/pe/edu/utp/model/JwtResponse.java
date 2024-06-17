package pe.edu.utp.model;

public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }

    //Getters & Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
