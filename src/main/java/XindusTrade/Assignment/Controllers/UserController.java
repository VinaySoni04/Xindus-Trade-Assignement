package XindusTrade.Assignment.Controllers;

import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    private ResponseEntity<UserResponseDTO> addUser(@RequestBody User user){
        try {
            UserResponseDTO response=userService.add(user); // Calling add() function which is defined in service layer
            // Setting the status message and code
            response.setStatusCode("202");
            response.setStatusMessage("SUCCESS!! User added successfully!!");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e){
            UserResponseDTO response=new UserResponseDTO();
            // Setting the status message and code
            response.setStatusCode("400");
            response.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
