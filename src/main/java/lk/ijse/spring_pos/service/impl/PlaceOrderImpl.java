//
//package lk.ijse.spring_pos.service.impl;
//
//import jakarta.transaction.Transactional;
//import lk.ijse.spring_pos.dto.OrderDTO;
//import lk.ijse.spring_pos.entity.Item;
//import lk.ijse.spring_pos.entity.Order;
//import lk.ijse.spring_pos.entity.OrderDetail;
//import lk.ijse.spring_pos.repo.CustomerRepo;
//import lk.ijse.spring_pos.repo.ItemRepo;
//import lk.ijse.spring_pos.repo.OrderDetailRepo;
//import lk.ijse.spring_pos.repo.OrderRepo;
//import lk.ijse.spring_pos.service.PlaceOrderService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.util.Optional;
//
//@Service
//@Transactional
//public class PlaceOrderImpl implements PlaceOrderService {
//
//    @Autowired
//    private OrderRepo orderRepo;
//
//    @Autowired
//    private OrderDetailRepo orderDetailRepo;
//
//    @Autowired
//    private ItemRepo itemRepo;
//
//    @Autowired
//    private CustomerRepo customerRepo;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//
//    @Override
//    public void addOrder(OrderDTO orderDTO) {
//        System.out.println("OrderDTO Received: " + orderDTO);
//
//        Order order = modelMapper.map(orderDTO, Order.class);
//        System.out.println("Mapped Order: " + order);
//
//        // Check if order already exists
//        if (orderRepo.existsById(order.getOrderId())) {
//            throw new RuntimeException("Order already exists");
//        }
//
//        // Save the order
//        orderRepo.save(order);
//        System.out.println("Order saved successfully!");
//        for (OrderDetail orderDetail : order.getOrderDetails()) {
//            Optional<Item> resp = itemRepo.findById(orderDetail.getItem().getCode());
//            if (!resp.isPresent()) {
//                throw new RuntimeException("Item not found");
//            }
//            System.out.println("Item found: " + resp.get());
//
//            // Update quantity
//            Item item = resp.get();
//            item.setQuantity(item.getQuantity() - orderDetail.getQuantity());
//            itemRepo.save(item);
//
//            orderDetailRepo.save(orderDetail);
//            System.out.println("Order detail saved: " + orderDetail);
//        }
//    }
//}

package lk.ijse.spring_pos.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.spring_pos.dto.OrderDTO;
import lk.ijse.spring_pos.dto.OrderDetailDTO;
import lk.ijse.spring_pos.entity.Customer;
import lk.ijse.spring_pos.entity.Item;
import lk.ijse.spring_pos.entity.Orders;
import lk.ijse.spring_pos.entity.OrderDetail;
import lk.ijse.spring_pos.repo.CustomerRepo;
import lk.ijse.spring_pos.repo.ItemRepo;
import lk.ijse.spring_pos.repo.OrderDetailRepo;
import lk.ijse.spring_pos.repo.OrderRepo;
import lk.ijse.spring_pos.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceOrderImpl implements PlaceOrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void addOrder(OrderDTO orderDTO) {

        Orders order = new Orders();
        order.setOrderId(orderDTO.getOrderId());
        order.setDate(orderDTO.getDate());

        Customer customer = customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + orderDTO.getCustomerId()));

        order.setCustomer(customer);

        order.setTotalPrice(orderDTO.getTotalPrice());

        Orders savedOrder = orderRepo.save(order);

        List<OrderDetailDTO> orderDetailsList = orderDTO.getOrderDetails();

        for (OrderDetailDTO orderDetailDTO : orderDetailsList) {
            Item item = itemRepo.findById(orderDetailDTO.getItemCode())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + orderDetailDTO.getItemCode()));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setItem(item);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            orderDetail.setTotal(orderDetailDTO.getTotal());

            orderDetailRepo.save(orderDetail);

            itemRepo.updateQty(item.getCode(),orderDetailDTO.getQuantity());

        }
    }

}
