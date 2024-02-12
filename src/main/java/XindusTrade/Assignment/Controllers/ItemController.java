package XindusTrade.Assignment.Controllers;

import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping("add")
    private String addItem(Item item){
        return itemService.add(item);
    }
}
