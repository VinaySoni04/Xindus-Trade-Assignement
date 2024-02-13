package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Exceptions.NotFoundException;
import XindusTrade.Assignment.Repositories.ItemRepository;
import XindusTrade.Assignment.Repositories.UserRepository;
import XindusTrade.Assignment.Transformers.ItemTransformer;
import XindusTrade.Assignment.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;
    public UserResponseDTO add(UserRequestDTO userDTO) throws RuntimeException{
        // We are converting dto to entity because function is taking dto as input and storing entity in database.
        // So we need entity, not a dto
        User user=UserTransformer.convertDtoToEntity(userDTO);
        if(user.getUsername()==null || user.getPassword()==null)
            throw new RuntimeException("Please enter required details!!"); // We can create custom exceptions like UsernameNotFoundException etc
        userRepository.save(user); // Storing user in database
        UserResponseDTO responseDTO=UserTransformer.convertEntityToDto(user);
        return responseDTO;
    }

    public List<Item> getWishList(Long userId) throws NotFoundException {
        Optional<User> userOpt=userRepository.findById(userId);
        if (userOpt.isEmpty())
            throw new NotFoundException("User is not found with mentioned id");
        User user=userOpt.get();
        List<Item> wishlist=user.getWishlist();
        if (wishlist.size()==0)
            throw new RuntimeException("Wishlist is empty. Please add item first");
        return wishlist;
    }

    public ItemResponseDTO addItemToWishlist(Long userId, Long itemId) throws NotFoundException {
        Optional<Item> itemOpt=itemRepository.findById(itemId);
        Optional<User> userOpt=userRepository.findById(userId);
        if (itemOpt.isEmpty() || userOpt.isEmpty())
            throw new NotFoundException("Please provide required details");
        Item item=itemOpt.get();
        User user=userOpt.get();
        user.getWishlist().add(item);
        userRepository.save(user);
        ItemResponseDTO responseDTO=ItemTransformer.convertEntityToDto(item);
        return responseDTO;
    }

    public ItemResponseDTO removeItemFromWishlist(Long userId, Long itemId) throws NotFoundException {
        Optional<Item> itemOpt=itemRepository.findById(itemId);
        Optional<User> userOpt=userRepository.findById(userId);
        if (itemOpt.isEmpty() || userOpt.isEmpty())
            throw new NotFoundException("Please provide required details");
        User user=userOpt.get();
        Item item=itemOpt.get();
        List<Item> updatedWishlist=user.getWishlist();
        for (Item i:updatedWishlist){
            if (i.getId()!=itemId)
                updatedWishlist.add(i);
        }
        user.setWishlist(updatedWishlist);
        userRepository.save(user);
        ItemResponseDTO responseDTO=ItemTransformer.convertEntityToDto(item);
        return responseDTO;
    }
}
