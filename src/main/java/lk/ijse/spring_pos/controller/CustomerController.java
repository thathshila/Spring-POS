package lk.ijse.spring_pos.controller;

import lk.ijse.spring_pos.dto.CustomerDTO;
import lk.ijse.spring_pos.service.CustomerService;
import lk.ijse.spring_pos.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping( "save")
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return new ResponseUtil(201,"Customer Saved",null);
    }

    @PutMapping("update")
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
        return new ResponseUtil(200,"Customer Updated",null);

    }
    @DeleteMapping(path = "delete/{id}")
    public ResponseUtil deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return new ResponseUtil(200,"Customer Deleted",null);
    }

   // @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("getAll")
    public ResponseUtil getAllCustomers() {
        return new ResponseUtil(
                200,
                "Customer List",
                customerService.getAllCustomers());
    }
    @GetMapping("getCustomerPhone")
    public ResponseUtil getCustomersPhone() {
        return new ResponseUtil(
                200,
                "Customer Phone List",
                customerService.getCustomersPhone());
    }
    @GetMapping("getCustomerByPhone/{phone}")
    public ResponseUtil getCustomerByPhone(@PathVariable String phone) {
        String customerName = customerService.getCustomerNameByPhone(phone);
            return new ResponseUtil(200, "Customer found", customerName);
    }
}
