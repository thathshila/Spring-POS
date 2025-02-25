package lk.ijse.spring_pos.service;

import lk.ijse.spring_pos.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItem(String code);
    List<ItemDTO> getAllItems();
    List<String> getItemCodes();
    ItemDTO getItemByCode(String code);
}
