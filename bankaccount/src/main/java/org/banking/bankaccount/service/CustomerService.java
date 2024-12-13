package org.banking.bankaccount.service;


import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.exception.NotFoundException;
import org.banking.bankaccount.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomerDetails(Long customerId) {

        CustomerDto customerDto = new CustomerDto();
        Customer customer = Optional.ofNullable(customerRepository.findCustomersById(customerId))
                        .orElseThrow(()-> new NotFoundException("Customer not found"));
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setAccount(customer.getAccount());
        return customerDto;
    }
}
