package lk.ijse.spring_pos.service.impl;


import lk.ijse.spring_pos.dto.CustomerDTO;
import lk.ijse.spring_pos.entity.Customer;
import lk.ijse.spring_pos.repo.CustomerRepo;
import lk.ijse.spring_pos.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
            throw new RuntimeException("Customer already exists");
        }
        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        }
        throw new RuntimeException("Customer does not exist");
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return modelMapper.map(customerRepo.findAll(),
                new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    @Override
    public List<String> getCustomersPhone() {
        return customerRepo.getCustomersPhone();
    }
}
