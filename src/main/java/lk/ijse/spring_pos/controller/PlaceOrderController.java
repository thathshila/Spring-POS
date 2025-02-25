package lk.ijse.spring_pos.controller;

import lk.ijse.spring_pos.dto.OrderDTO;
import lk.ijse.spring_pos.service.PlaceOrderService;
import lk.ijse.spring_pos.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/order")
public class  PlaceOrderController {

    @Autowired
    private PlaceOrderService placeOrderService;

    @PostMapping("save")
    public ResponseUtil saveOrder(@RequestBody OrderDTO orderDTO) {
        placeOrderService.addOrder(orderDTO);
        return new ResponseUtil(201,"Order Saved",null);
    }
}
