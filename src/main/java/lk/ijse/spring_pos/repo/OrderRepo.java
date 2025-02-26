package lk.ijse.spring_pos.repo;


import lk.ijse.spring_pos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, String> {
}
