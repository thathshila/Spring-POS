package lk.ijse.spring_pos.service;

import lk.ijse.spring_pos.dto.OrderDTO;
import lk.ijse.spring_pos.dto.OrderDetailDTO;

import java.util.List;

public interface PlaceOrderService {

    void addOrder(OrderDTO orderDTO);

    List<OrderDetailDTO> getOrderDetails();
}
