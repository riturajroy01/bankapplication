package org.banking.bankaccount.service;


import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.exception.NotFoundException;
import org.banking.bankaccount.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public CustomerDto getCustomerDetails(Long customerId) {

        Customer customer = Optional.ofNullable(customerRepository.findCustomersById(customerId))
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .account(customer.getAccount())
                .build();
    }
}
