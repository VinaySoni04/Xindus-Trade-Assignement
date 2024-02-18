package XindusTrade.Assignment.Controllers;

import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.AuthResponseDTO;
import XindusTrade.Assignment.Services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserRequestDTO requestDTO) {
        doAuthenticate(requestDTO.getUsername(),requestDTO.getPassword()); // Calling doAuthenticate() function which authenticates the user.
        UserDetails userDetails = customUserDetailService.loadUserByUsername(requestDTO.getUsername());
        AuthResponseDTO responseDTO = AuthResponseDTO.builder()
                .username(userDetails.getUsername())
                .statusCode("200")
                .statusMessage("Logged In")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid!!";
    }
}
