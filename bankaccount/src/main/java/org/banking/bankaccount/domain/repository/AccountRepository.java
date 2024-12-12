package org.banking.bankaccount.domain.repository;


import org.banking.bankaccount.domain.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<CustomerAccount, String> {
}
