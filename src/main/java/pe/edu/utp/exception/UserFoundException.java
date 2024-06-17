package pe.edu.utp.exception;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super("El usuario con ese username ya existe, ingrese un username distinto");
    }

    public UserFoundException(String mensaje){
        super(mensaje);
    }

}
