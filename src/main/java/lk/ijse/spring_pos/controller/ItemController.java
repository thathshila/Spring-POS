package lk.ijse.spring_pos.controller;

import lk.ijse.spring_pos.dto.ItemDTO;
import lk.ijse.spring_pos.service.ItemService;
import lk.ijse.spring_pos.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping( value = "save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveItem(@RequestBody ItemDTO itemDTO) {
        itemService.addItem(itemDTO);
        return new ResponseUtil(201,"Item Saved",null);
    }

    @PutMapping(value = "update",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateItem(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
        return new ResponseUtil(200,"Item Updated",null);
    }
    @DeleteMapping(path = "delete/{code}")
    public ResponseUtil deleteItem(@PathVariable("code") String code) {
        itemService.deleteItem(code);
        return new ResponseUtil(200,"Item Deleted",null);
    }
    @GetMapping("getAll")
    public ResponseUtil getAllItems() {
        return new ResponseUtil(200,"Get All Items",itemService.getAllItems());
    }
    @GetMapping("getItemCodes")
    public ResponseUtil getItemCodes() {
        return new ResponseUtil(200,"Get All Item Codes",itemService.getItemCodes());
    }
}
