package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public String add(Item item) {
        itemRepository.save(item);
        return "Item added successfully!!";
    }
}
