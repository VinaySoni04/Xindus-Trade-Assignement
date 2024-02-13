package XindusTrade.Assignment.Controllers;

import XindusTrade.Assignment.DTOs.RequestDTOs.ItemRequestDTO;
import XindusTrade.Assignment.DTOs.ResponseDTOs.ItemResponseDTO;
import XindusTrade.Assignment.Entities.Item;
import XindusTrade.Assignment.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping("addItem")
    private ResponseEntity<ItemResponseDTO> addItem(ItemRequestDTO itemDTO){
        try {
            ItemResponseDTO response=itemService.add(itemDTO);
            response.setStatusCode("202");
            response.setStatusMessage("SUCCESS!! Item added successfully!!");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e){
            ItemResponseDTO response=new ItemResponseDTO();
            response.setStatusCode("400");
            response.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
