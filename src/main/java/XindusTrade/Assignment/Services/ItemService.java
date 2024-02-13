package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Repositories.ItemRepository;
import XindusTrade.Assignment.Transformers.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public ItemResponseDTO add(ItemRequestDTO itemDTO) throws RuntimeException{
        Item item=ItemTransformer.convertDtoToEntity(itemDTO);
        if (item.getName()==null || item.getDescription()==null || item.getPrice()==0.0 || item.getNumberOfUnits()==0)
        throw new RuntimeException("Please provide required details");
        itemRepository.save(item);
        ItemResponseDTO responseDTO=ItemTransformer.convertEntityToDto(item);
        return responseDTO;
    }
}
