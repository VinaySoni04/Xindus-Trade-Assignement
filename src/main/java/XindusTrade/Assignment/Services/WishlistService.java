package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Exceptions.NotFoundException;
import XindusTrade.Assignment.Repositories.UserRepository;
import XindusTrade.Assignment.Transformers.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private UserRepository userRepository;
    public List<Item> getWishList(String username) throws NotFoundException {
        User user=userRepository.findByUsername(username);
        List<Item> wishlist=user.getWishlist();
        if (wishlist.size()==0){
            throw new RuntimeException("Wishlist is empty. Please add item first");
        }
        return wishlist;
    }

    public ItemResponseDTO addItemToWishlist(String username, ItemRequestDTO itemDTO){
        Item item=ItemTransformer.convertDtoToEntity(itemDTO);
        User user=userRepository.findByUsername(username);
        user.getWishlist().add(item);
        userRepository.save(user);
        ItemResponseDTO responseDTO=ItemTransformer.convertEntityToDto(item);
        return responseDTO;
    }

    public String removeItemFromWishlist(String username, Long itemId) throws NotFoundException {
        User user=userRepository.findByUsername(username);
        List<Item> updatedWishlist=user.getWishlist();
        for (Item i:updatedWishlist){
            if (i.getId()!=itemId)
                updatedWishlist.add(i);
        }
        user.setWishlist(updatedWishlist);
        userRepository.save(user);
        return "Item with id:-"+itemId+" is removed from wishlist";
    }
}
