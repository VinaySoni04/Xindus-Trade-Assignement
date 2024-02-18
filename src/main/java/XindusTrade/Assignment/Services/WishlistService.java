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

    // The function takes a String parameter username, representing the username for which the wishlist needs to be retrieved.
    public List<Item> getWishList(String username) throws NotFoundException {
        User user=userRepository.findByUsername(username); // It retrieves the user entity corresponding to the given username from the database.
                                                           // The method findByUsername is provided by Spring Data JPA
        List<Item> wishlist=user.getWishlist(); // The method getWishlist() retrieves the wishlist associated with that user.
        if (wishlist.size()==0){
            throw new RuntimeException("Wishlist is empty. Please add item first"); // If the size of list is zero, it means the wishlist is empty, it throws an exception
        }
        return wishlist;
    }

    // The function takes a String parameter username and ItemRequestDTO itemDTO, representing the username for which the item has to be added to the wishlist.
    public ItemResponseDTO addItemToWishlist(String username, ItemRequestDTO itemDTO){
        Item item=ItemTransformer.convertDtoToEntity(itemDTO); // It converts the ItemRequestDTO object into an Item entity because we have to do operations on entity, not on dto
        if (item.getNumberOfUnits()>0){ // It checks if the numberOfUnits of the item is greater than zero.
                                        // If so, it sets the available flag of the item to true. It means item is in stock.
                                        // Note:- This attribute has no use
            item.setAvailable(true);
        }
        User user=userRepository.findByUsername(username);
        user.getWishlist().add(item); // It adds the item to the user's wishlist retrieved from the user entity.
        item.getUsers().add(user); // It marks item by user
        userRepository.save(user); // Save the user entity with the updated wishlist
        ItemResponseDTO responseDTO=ItemTransformer.convertEntityToDto(item); // Convert the added item back to a DTO for response
        return responseDTO;
    }

    // The function takes a String parameter username and integer parameter item id, representing the username for which the item has to be deleted from the wishlist.
    public String removeItemFromWishlist(String username, int itemId) throws NotFoundException {
        User user=userRepository.findByUsername(username);
        List<Item> list=user.getWishlist(); // Getting the wishlist of user
        for (Item i:list){ // Iterating the list and removing the item which has id equal to required item id.
            if (i.getId()==itemId)
                list.remove(i);
        }
        user.setWishlist(list); // Updating the wishlist.
        userRepository.save(user);
        return "Item with id:-"+itemId+" is removed from wishlist";
    }
}
