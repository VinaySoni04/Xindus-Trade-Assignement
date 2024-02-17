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
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            return new ResponseEntity<>(wishlistService.getWishList(currentUsername),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/wishlists")
    public ResponseEntity<ItemResponseDTO> addItem(@RequestBody ItemRequestDTO itemDTO){
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        ItemResponseDTO response=wishlistService.addItemToWishlist(currentUsername,itemDTO);
        response.setStatusCode("202");
        response.setStatusMessage("SUCCESS!! Item added successfully!!");
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/wishlists/{itemId}")
    public ResponseEntity<String> removeItemFromWishlist(@PathVariable int itemId) throws NotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(wishlistService.removeItemFromWishlist(username,itemId),HttpStatus.ACCEPTED);
    }
}
