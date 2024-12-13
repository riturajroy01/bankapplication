package org.banking.bankaccount.repository;


import org.banking.bankaccount.domain.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<CustomerAccount, Long> {
}
