package XindusTrade.Assignment.Controllers;

import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Exceptions.NotFoundException;
import XindusTrade.Assignment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    private ResponseEntity<UserResponseDTO> addUser(UserRequestDTO userDTO){
        try {
            UserResponseDTO response=userService.add(userDTO);
            response.setStatusCode("202");
            response.setStatusMessage("SUCCESS!! User added successfully!!");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e){
            UserResponseDTO response=new UserResponseDTO();
            response.setStatusCode("400");
            response.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/wishlists")
    private ResponseEntity<?> getWishlist(Long userId) throws NotFoundException {
        try {
            return new ResponseEntity<>(userService.getWishList(userId),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/wishlists")
    private ResponseEntity<ItemResponseDTO> addItemToWishlist(@RequestBody Long userId, @RequestBody Long itemId) throws NotFoundException {
        try {
            ItemResponseDTO response=userService.addItemToWishlist(userId,itemId);
            response.setStatusCode("200");
            response.setStatusMessage("SUCCESS!! Item added to wishlist");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e){
            ItemResponseDTO response=new ItemResponseDTO();
            response.setStatusCode("404");
            response.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/wishlists/{userId}")
    private ResponseEntity<ItemResponseDTO> removeItemFromWishlist(@PathVariable Long userId, @RequestBody Long itemId) throws NotFoundException {
        return new ResponseEntity<>(userService.removeItemFromWishlist(userId,itemId),HttpStatus.ACCEPTED);
    }
}
