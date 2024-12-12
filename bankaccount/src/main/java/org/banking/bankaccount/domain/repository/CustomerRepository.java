package org.banking.bankaccount.domain.repository;



import org.banking.bankaccount.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer, String> {
}
