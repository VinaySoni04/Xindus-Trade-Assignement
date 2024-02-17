package XindusTrade.Assignment.ControllerTests;

import XindusTrade.Assignment.Controllers.AuthController;
import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.AuthResponseDTO;
import XindusTrade.Assignment.Services.CustomUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private CustomUserDetailService customUserDetailService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthController authController;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void testLogin_Success() {
        // Mock data
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("testUser");
        requestDTO.setPassword("password123");
        UserDetails userDetails = new UserDetailsImp();

        // Mocking authentication manager behavior
        doNothing().when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Mocking user details service behavior
        when(customUserDetailService.loadUserByUsername(requestDTO.getUsername())).thenReturn(userDetails);

        // Call the controller method
        ResponseEntity<AuthResponseDTO> responseEntity = authController.login(requestDTO);

        // Verify the response entity
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());

        // Verify the response DTO
        AuthResponseDTO responseDTO = responseEntity.getBody();
        assertNotNull(responseDTO);
        assertEquals("testUser", responseDTO.getUsername());
        assertEquals("200", responseDTO.getStatusCode());
        assertEquals("Logged In", responseDTO.getStatusMessage());

        // Verify that the authentication manager was called
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        // Verify that the user details service was called
        verify(customUserDetailService).loadUserByUsername(requestDTO.getUsername());
    }

    @Test
    void testLogin_Failure_BadCredentials() {
        // Test case for bad credentials
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("testUser");
        requestDTO.setPassword("password123");

        // Mocking authentication manager behavior to throw BadCredentialsException
        doThrow(BadCredentialsException.class).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Call the controller method and expect BadCredentialsException
        assertThrows(BadCredentialsException.class, () -> authController.login(requestDTO));

        // Verify that the authentication manager was called
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        // Verify that the user details service was not called
        verifyNoInteractions(customUserDetailService);
    }
}
