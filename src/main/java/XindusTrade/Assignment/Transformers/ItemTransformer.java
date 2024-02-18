package XindusTrade.Assignment.Transformers;

import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.RequestDTOs.UserRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.UserResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Entities.User;

public class ItemTransformer {
    public static Item convertDtoToEntity(ItemRequestDTO itemDTO){
        if (itemDTO==null || itemDTO.getPrice()==null) {
            // Handle the case where itemDTO or its price is null
            throw new IllegalArgumentException("ItemRequestDTO or its price cannot be null");
        }
        Item itemObj=Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .numberOfUnits(itemDTO.getNumberOfUnits())
                .price(itemDTO.getPrice().doubleValue())
                .build();
        return itemObj;
    }

    public static ItemResponseDTO convertEntityToDto(Item item) {
        ItemResponseDTO itemResponseDTO=ItemResponseDTO.builder()
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .numberOfUnits(item.getNumberOfUnits())
                .build();
        return itemResponseDTO;
    }
}
