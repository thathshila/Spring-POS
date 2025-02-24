package lk.ijse.spring_pos.repo;

import lk.ijse.spring_pos.dto.ItemDTO;
import lk.ijse.spring_pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, String> {
    @Query("SELECT i.code FROM Item i") // Corrected JPQL query
    List<String> findAllItemCodes();
}

