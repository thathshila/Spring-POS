package lk.ijse.spring_pos.service.impl;

import lk.ijse.spring_pos.dto.ItemDTO;
import lk.ijse.spring_pos.entity.Item;
import lk.ijse.spring_pos.repo.ItemRepo;
import lk.ijse.spring_pos.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getCode())){
            throw new RuntimeException("Item already exists");
        }
        itemRepo.save(modelMapper.map(itemDTO, Item.class));

    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getCode())){
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
        }
        throw new RuntimeException("Item does not exist");
    }

    @Override
    public void deleteItem(String code) {
        itemRepo.deleteById(code);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return modelMapper.map(itemRepo.findAll(),
                new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public List<String> getItemCodes() {
        return itemRepo.findAllItemCodes();
    }

    @Override
    public ItemDTO getItemByCode(String code) {
        return itemRepo.findItemByCode(code);
    }
}
