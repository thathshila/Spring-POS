
package lk.ijse.spring_pos.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.spring_pos.dto.OrderDTO;
import lk.ijse.spring_pos.entity.Item;
import lk.ijse.spring_pos.entity.Order;
import lk.ijse.spring_pos.entity.OrderDetail;
import lk.ijse.spring_pos.repo.CustomerRepo;
import lk.ijse.spring_pos.repo.ItemRepo;
import lk.ijse.spring_pos.repo.OrderDetailRepo;
import lk.ijse.spring_pos.repo.OrderRepo;
import lk.ijse.spring_pos.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Transactional
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

//    @Override
//    public void addOrder(OrderDTO orderDTO) {
//        Order order = modelMapper.map(orderDTO, Order.class);
//        System.out.println(order);
//
//        // Fix: Directly check orderId as it's already a String
//        if (orderRepo.existsById(order.getOrderId())) {
//            throw new RuntimeException("Order already exists");
//        }
//
//        // Save the order first
//        orderRepo.save(order);
//
//        // Save order details and update item quantities
//        for (OrderDetail orderDetail : order.getOrderDetails()) {
//            Optional<Item> resp = itemRepo.findById(orderDetail.getItem().getCode());
//            if (!resp.isPresent()) {
//                throw new RuntimeException("Item not found");
//            }
//
//            Item item = resp.get();
//            if (item.getQuantity() < orderDetail.getQuantity()) {
//                throw new RuntimeException("Insufficient stock for item: " + item.getCode());
//            }
//
//            item.setQuantity(item.getQuantity() - orderDetail.getQuantity());
//            itemRepo.save(item);
//
//            // Ensure orderDetail is linked to the saved order
//            orderDetail.setOrder(order);
//            orderDetailRepo.save(orderDetail);
//        }
//    }

    @Override
    public void addOrder(OrderDTO orderDTO) {
        System.out.println("OrderDTO Received: " + orderDTO);

        Order order = modelMapper.map(orderDTO, Order.class);
        System.out.println("Mapped Order: " + order);

        // Check if order already exists
        if (orderRepo.existsById(order.getOrderId())) {
            throw new RuntimeException("Order already exists");
        }

        // Save the order
        orderRepo.save(order);
        System.out.println("Order saved successfully!");

        // Save order details and update item quantities
//        for (OrderDetail orderDetail : order.getOrderDetails()) {
//            System.out.println("Processing OrderDetail: " + orderDetail);
//
//            Optional<Item> resp = itemRepo.findById(orderDetail.getItem().getCode());
//            if (!resp.isPresent()) {
//                throw new RuntimeException("Item not found: " + orderDetail.getItem().getCode());
//            }
//
//            Item item = resp.get();
//            if (item.getQuantity() < orderDetail.getQuantity()) {
//                throw new RuntimeException("Insufficient stock for item: " + item.getCode());
//            }
//
//            item.setQuantity(item.getQuantity() - orderDetail.getQuantity());
//            itemRepo.save(item);
//            System.out.println("Item updated: " + item);
//
//            orderDetail.setOrder(order);
//            orderDetailRepo.save(orderDetail);
//            System.out.println("OrderDetail saved: " + orderDetail);
//        }
//
//        System.out.println("Order placed successfully!");
//    }
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            Optional<Item> resp = itemRepo.findById(orderDetail.getItem().getCode());
            if (!resp.isPresent()) {
                throw new RuntimeException("Item not found");
            }
            System.out.println("Item found: " + resp.get());

            // Update quantity
            Item item = resp.get();
            item.setQuantity(item.getQuantity() - orderDetail.getQuantity());
            itemRepo.save(item);

            orderDetailRepo.save(orderDetail);
            System.out.println("Order detail saved: " + orderDetail);
        }
    }
}
