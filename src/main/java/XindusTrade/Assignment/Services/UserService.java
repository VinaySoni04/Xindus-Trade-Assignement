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

    // Function takes a User object as input, representing the user you want to add to the database.
    public UserResponseDTO add(User user) throws RuntimeException{
        // It checks if the username and password fields of the user are empty.
        // If either of them is empty, it throws a RuntimeException with a message indicating that required details are missing.
        if(user.getUsername()=="" || user.getPassword()=="")
            throw new RuntimeException("Please enter required details!!"); // We can create custom exceptions like UsernameNotFoundException etc.
        user.setPassword(passwordEncoder.encode(user.getPassword())); // It encodes the password using the injected passwordEncoder bean.
        userRepository.save(user); // Storing user in database
        UserResponseDTO responseDTO=UserTransformer.convertEntityToDto(user); // It converts the saved User object into a UserResponseDTO object because function returns a response dto.
        return responseDTO;
    }
}
