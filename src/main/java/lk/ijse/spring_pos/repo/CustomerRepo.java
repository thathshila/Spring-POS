package lk.ijse.spring_pos.repo;


import lk.ijse.spring_pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, String> {
}
