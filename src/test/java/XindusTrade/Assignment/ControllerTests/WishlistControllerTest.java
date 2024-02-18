package XindusTrade.Assignment.ControllerTests;

import XindusTrade.Assignment.Controllers.WishlistController;
import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Exceptions.NotFoundException;
import XindusTrade.Assignment.Services.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    @Test
    void testGetWishlist() throws NotFoundException {
        String username = "testUser";
        List<Item> wishlistItems = Arrays.asList(
                new Item(1231, "Item1", "ABC", 200.0, 20, true),
                new Item(1298, "Item2", "XYZ", 50.0, 20, true)
        );
        // Mock the SecurityContextHolder to return the test username
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null));
        when(wishlistService.getWishList(username)).thenReturn(wishlistItems);
        ResponseEntity<?> response = wishlistController.getWishlist();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishlistItems, response.getBody());
    }

    @Test
    void testAddItem() {
        ItemRequestDTO itemDTO = new ItemRequestDTO("Item1", "ABC", 200.0, 20);
        String currentUsername = "testUser";
        ItemResponseDTO responseDTO = new ItemResponseDTO();
        // Mock the SecurityContextHolder to return a mocked authentication object
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(currentUsername, null));
        when(wishlistService.addItemToWishlist(currentUsername, itemDTO)).thenReturn(responseDTO);
        ResponseEntity<ItemResponseDTO> response = wishlistController.addItem(itemDTO);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }


    @Test
    void testRemoveItemFromWishlist() throws NotFoundException {
        int itemId = 1;
        String username = "testUser";
        String expectedResult = "Item removed successfully";
        // Mock the SecurityContextHolder to return a mocked authentication object
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null));
        when(wishlistService.removeItemFromWishlist(username, itemId)).thenReturn(expectedResult);
        ResponseEntity<String> response = wishlistController.removeItemFromWishlist(itemId);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }
}
