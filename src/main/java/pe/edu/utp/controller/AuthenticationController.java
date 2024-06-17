package pe.edu.utp.controller;


import pe.edu.utp.exception.UserNotFoundException;
import pe.edu.utp.implement.UserDetailsServiceImpl;
import pe.edu.utp.model.User;
import pe.edu.utp.model.JwtRequest;
import pe.edu.utp.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.utils.JwtUtils;


import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getEmail(),jwtRequest.getPassword());
        }catch (UserNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales inválidas " + e.getMessage());
        }
    }

    @GetMapping("/actual-usuario")
    public User obtenerUsuarioActual(Principal principal){
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
