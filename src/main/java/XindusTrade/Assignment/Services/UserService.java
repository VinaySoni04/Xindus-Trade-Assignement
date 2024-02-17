package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Repositories.UserRepository;
import XindusTrade.Assignment.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserResponseDTO add(User user) throws RuntimeException{
        if(user.getUsername()=="" || user.getPassword()=="")
            throw new RuntimeException("Please enter required details!!"); // We can create custom exceptions like UsernameNotFoundException etc
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); // Storing user in database
        UserResponseDTO responseDTO=UserTransformer.convertEntityToDto(user);
        return responseDTO;
    }
}
