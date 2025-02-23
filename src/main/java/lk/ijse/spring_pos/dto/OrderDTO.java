package lk.ijse.spring_pos.dto;


import lk.ijse.spring_pos.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String orderId;
    private double totalPrice;
    private Date date;
    private Customer customer;
}
