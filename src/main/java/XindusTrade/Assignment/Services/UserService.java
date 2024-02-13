package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Repositories.UserRepository;
import XindusTrade.Assignment.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
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
}
