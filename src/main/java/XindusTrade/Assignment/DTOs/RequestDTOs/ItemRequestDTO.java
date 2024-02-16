package XindusTrade.Assignment.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    private String name;
    private String description;
    private Double price;
    private int numberOfUnits;
}
