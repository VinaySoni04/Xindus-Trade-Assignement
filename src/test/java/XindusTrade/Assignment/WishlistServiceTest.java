package XindusTrade.Assignment;


import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Exceptions.NotFoundException;
import XindusTrade.Assignment.Repositories.UserRepository;
import XindusTrade.Assignment.Services.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WishlistService wishlistService;
    @Test
    void testGetWishlistNotEmpty() throws NotFoundException {
        String username = "testUser";
        User user = new User();
        Item item1 = new Item();
        item1.setId(1);
        Item item2 = new Item();
        item2.setId(2);
        List<Item> wishlist = new ArrayList<>();
        wishlist.add(item1);
        wishlist.add(item2);
        user.setWishlist(wishlist);
        when(userRepository.findByUsername(username)).thenReturn(user);
        List<Item> result = wishlistService.getWishList(username);
        assertEquals(2, result.size());
        assertEquals(item1.getId(), result.get(0).getId());
        assertEquals(item2.getId(), result.get(1).getId());
    }

    @Test
    void testAddItemToWishlist() {
        String username = "testUser";
        ItemRequestDTO itemDTO = new ItemRequestDTO();
        Item item = new Item();
        ItemResponseDTO responseDTO = new ItemResponseDTO();
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        ItemResponseDTO result = wishlistService.addItemToWishlist(username, itemDTO);
        assertNotNull(result);
    }

    @Test
    void testRemoveItemFromWishlist() throws NotFoundException {
        String username = "testUser";
        Long itemId = 1L;
        User user = new User();
        Item item1 = new Item();
        item1.setId(1);
        Item item2 = new Item();
        item2.setId(2);
        List<Item> wishlist = new ArrayList<>();
        wishlist.add(item1);
        wishlist.add(item2);
        user.setWishlist(wishlist);
        when(userRepository.findByUsername(username)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        String result = wishlistService.removeItemFromWishlist(username, itemId);
        assertEquals("Item with id:-1 is removed from wishlist", result);
        assertEquals(1, user.getWishlist().size());
        assertEquals(item2.getId(), user.getWishlist().get(0).getId());
    }
}
