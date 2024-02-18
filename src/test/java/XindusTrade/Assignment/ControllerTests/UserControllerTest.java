package XindusTrade.Assignment.ControllerTests;

import XindusTrade.Assignment.Controllers.UserController;
import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    public UserControllerTest() {
        this.userController = new UserController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testAddUser_Success() throws Exception {
        // Mock data
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        // Mocking service behavior
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setStatusCode("202");
        responseDTO.setStatusMessage("SUCCESS!! User added successfully!!");
        when(userService.add(user)).thenReturn(responseDTO);

        // Create JSON representation of the user object
        String userJson = objectMapper.writeValueAsString(user);

        // Build the request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        // Perform the request and assert the response
        performRequestAndAssert(requestBuilder, status().isAccepted());
    }

    @Test
    void testAddUser_Failure() throws Exception {
        // Mock data
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        // Mocking service behavior for failure scenario
        String errorMessage = "Username already exists";
        when(userService.add(user)).thenThrow(new RuntimeException(errorMessage));

        // Create JSON representation of the user object
        String userJson = objectMapper.writeValueAsString(user);

        // Build the request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        // Perform the request and assert the response
        performRequestAndAssert(requestBuilder, status().isBadRequest());
    }

    private void performRequestAndAssert(RequestBuilder requestBuilder, ResultMatcher resultMatcher) throws Exception {
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(resultMatcher)
                .andReturn();
    }
}
