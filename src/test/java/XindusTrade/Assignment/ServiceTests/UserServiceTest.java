package XindusTrade.Assignment.ServiceTests;

import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Repositories.UserRepository;
import XindusTrade.Assignment.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;
    @Test
    void testAddUser_Success() {
        // Mock data
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        // Mocking password encoder behavior
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        // Mocking repository behavior
        when(userRepository.save(user)).thenReturn(user);

        // Call the service method
        UserResponseDTO responseDTO = userService.add(user);

        // Verify that the response DTO is not null
        assertNotNull(responseDTO);
        // Assert that the response DTO contains the correct data
        assertEquals("testUser", responseDTO.getUsername());
        // You can add more assertions based on your response DTO structure

        // Verify that the password encoder was called with the correct password
        verify(passwordEncoder).encode(user.getPassword());
        // Verify that the repository's save method was called with the correct user
        verify(userRepository).save(user);
    }

    @Test
    void testAddUser_InvalidInput() {
        // Test case for invalid input (empty username or password)
        User user = new User(); // Empty user object

        // Call the service method and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> userService.add(user));

        // Verify that the repository's save method was not called
        verifyNoInteractions(userRepository);
        // Verify that the password encoder was not called
        verifyNoInteractions(passwordEncoder);
    }
}
