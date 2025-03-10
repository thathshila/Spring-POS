package lk.ijse.spring_pos.repo;

import lk.ijse.spring_pos.dto.ItemDTO;
import lk.ijse.spring_pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, String> {
    @Query("SELECT i.code FROM Item i") // Corrected JPQL query
    List<String> findAllItemCodes();

//    @Query("SELECT i.name,i.price,i.quantity FROM Item i WHERE i.code = :code")
//    ItemDTO findItemByCode(String code);

    @Query("SELECT new lk.ijse.spring_pos.dto.ItemDTO(i.name, i.price, i.quantity) FROM Item i WHERE i.code = :code")
    ItemDTO findItemByCode(String code);

    @Modifying
    @Query("UPDATE Item SET quantity = quantity - :quantity where code = :code")
    void updateQty(String code, int quantity);
}

