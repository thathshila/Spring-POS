package lk.ijse.spring_pos.repo;

import lk.ijse.spring_pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, String> {
}
