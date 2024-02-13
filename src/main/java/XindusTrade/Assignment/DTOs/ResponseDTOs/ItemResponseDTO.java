package XindusTrade.Assignment.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {
    private String name;
    private String description;
    private Double price;
    private int numberOfUnits;
    private String statusCode;
    private String statusMessage;
}
