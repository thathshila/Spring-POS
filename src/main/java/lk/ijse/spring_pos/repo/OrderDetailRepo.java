package lk.ijse.spring_pos.repo;

import lk.ijse.spring_pos.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
}
