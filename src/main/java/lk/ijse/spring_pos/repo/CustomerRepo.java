package lk.ijse.spring_pos.repo;


import lk.ijse.spring_pos.dto.CustomerDTO;
import lk.ijse.spring_pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    @Query("SELECT c.phone From Customer c")
    List<String> getCustomersPhone();

    @Query("SELECT c.name FROM Customer c WHERE c.phone = :phone")
    String findNameByPhone(@Param("phone") String phone);


}
