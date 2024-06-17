package pe.edu.utp.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("El usuario con ese username no existe, vuelva a intentarlo");
    }

    public UserNotFoundException(String mensaje){
        super(mensaje);
    }

}
