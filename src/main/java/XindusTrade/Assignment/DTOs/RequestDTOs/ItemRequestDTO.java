package XindusTrade.Assignment.DTOs.RequestDTOs;

import lombok.Data;

@Data
public class ItemRequestDTO {
    private String name;
    private String description;
    private Double price;
    private int numberOfUnits;
}
