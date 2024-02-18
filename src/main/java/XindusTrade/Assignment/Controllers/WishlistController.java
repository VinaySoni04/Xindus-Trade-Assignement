package XindusTrade.Assignment.Controllers;

import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.Exceptions.NotFoundException;
import XindusTrade.Assignment.Services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @GetMapping("/wishlists")
    public ResponseEntity<?> getWishlist() throws NotFoundException {
        try {
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName(); // It retrieves the username of the currently authenticated user from the SecurityContextHolder.
                                                                                                       // SecurityContextHolder is a class provided by Spring Security, a powerful authentication and access control framework for Java applications.
                                                                                                       //It's part of the Spring Security Context module.
            return new ResponseEntity<>(wishlistService.getWishList(currentUsername),HttpStatus.OK); // Calling getWishList() function which is defined in service layer
        }
        catch (Exception e){ // Catching the exception if exception is thrown
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            // ResponseEntity is a class provided by Spring Framework that represents an HTTP response.
        }
    }

    @PostMapping("/wishlists")
    public ResponseEntity<ItemResponseDTO> addItem(@RequestBody ItemRequestDTO itemDTO){
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName(); // It retrieves the username of the currently authenticated user from the SecurityContextHolder.
        ItemResponseDTO response=wishlistService.addItemToWishlist(currentUsername,itemDTO); // Calling addItemToWishlist() function which is defined in service layer
        // Setting the status message and code
        response.setStatusCode("202");
        response.setStatusMessage("SUCCESS!! Item added successfully!!");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/wishlists/{itemId}")
    public ResponseEntity<String> removeItemFromWishlist(@PathVariable int itemId) throws NotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); // It retrieves the username of the currently authenticated user from the SecurityContextHolder.
        return new ResponseEntity<>(wishlistService.removeItemFromWishlist(username,itemId),HttpStatus.ACCEPTED); // Calling removeItemFromWishlist() function which is defined in service layer
    }
}
