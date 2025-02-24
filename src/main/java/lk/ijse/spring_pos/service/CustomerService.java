package lk.ijse.spring_pos.service;

import lk.ijse.spring_pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    List<String> getCustomersPhone();
}
