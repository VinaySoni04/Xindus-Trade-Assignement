package XindusTrade.Assignment.Transformers;

import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.User;

public class UserTransformer {
    // Transformer is used to convert model from one format to another format like entity, dto etc.
    // Mainly, Transformer is used to convert the format or structure of messages.
    // Add and remove information to a message based on its content or external data sources.


    // Function names defines their functionality
    public static User convertDtoToEntity(UserRequestDTO userDTO){
        User userObj=User.builder().username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        return userObj;
    }

    public static UserResponseDTO convertEntityToDto(User user) {
        UserResponseDTO userResponseDTO=UserResponseDTO.builder()
                .username(user.getUsername())
                .build();
        return userResponseDTO;
    }
}
