package XindusTrade.Assignment.DTOs.RequestDTOs;

import lombok.Data;

@Data
public class UserRequestDTO {
    // We are using DTOs(Data Transfer Objects) to pass the details of User
    // DTOs are objects used to encapsulate data and transfer it between different layers of an application.
    // We have 2 DTOs
    // 1. Request DTOs
    // 2. Response DTOs
    // These DTOs used for handling incoming requests and outgoing responses

    // This is User Request DTO
    // It represents the structure of the data expected in a request payload
    private String username;
    private String password;
}
