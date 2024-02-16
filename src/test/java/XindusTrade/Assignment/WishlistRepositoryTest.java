package XindusTrade.Assignment;

import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Repositories.WishlistRepository;
import XindusTrade.Assignment.Services.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class WishlistRepositoryTest {
    @Mock
    private WishlistRepository wishlistRepository;
    @InjectMocks
    private WishlistService wishlistService;

    @Test
    void testFindByUsername() {
        String username = "testUser";
        Item item1 = new Item();
        item1.setId(1);
        Item item2 = new Item();
        item2.setId(2);
        List<Item> wishlistItems = Arrays.asList(item1, item2);
        when(wishlistRepository.findByUsername(username)).thenReturn(wishlistItems);
        List<Item> result = wishlistRepository.findByUsername(username);
        assertEquals(2, result.size());
        assertEquals(item1.getId(), result.get(0).getId());
        assertEquals(item2.getId(), result.get(1).getId());
    }
}
