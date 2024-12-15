package org.banking.bankaccount.repository;

import org.banking.bankaccount.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findCustomersById(Long id);
}
