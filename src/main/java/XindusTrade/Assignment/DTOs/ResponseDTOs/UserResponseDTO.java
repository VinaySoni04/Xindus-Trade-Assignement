package XindusTrade.Assignment.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    // This is User Response DTO
    // It represents the structure of the data returned by the application in response to client requests
    private String username;
    private String statusCode;
    private String statusMessage;
}
