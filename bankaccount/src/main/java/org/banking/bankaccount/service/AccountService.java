package org.banking.bankaccount.service;


import lombok.extern.slf4j.Slf4j;
import org.banking.bankaccount.domain.dto.CreateAccountRequest;
import org.banking.bankaccount.domain.dto.CustomerAccountDto;
import org.banking.bankaccount.domain.entity.CustomerAccount;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.exception.NotFoundException;
import org.banking.bankaccount.repository.AccountRepository;
import org.banking.bankaccount.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Slf4j
@Service
public class AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public CustomerAccountDto createAccount(CreateAccountRequest createAccountRequest) {

        Customer customer = customerRepository.findById(createAccountRequest.customerID())
                .orElseThrow(() ->
                        new NotFoundException("Customer not found with id customer id " + createAccountRequest.customerID()));
        CustomerAccount account = CustomerAccount.builder()
                .initialCredit(createAccountRequest.initialCredit())
                .startDate(getCurrentDate())
                .customer(customer)
                .transactions(new HashSet<>())
                .build();

        if (createAccountRequest.initialCredit().compareTo(BigDecimal.ZERO) > 0) {
            AccountTransaction transaction =
                    AccountTransaction.builder()
                            .amount(createAccountRequest.initialCredit())
                            .transactionDate(getCurrentDate())
                            .account(account)
                            .build();

            account.getTransactions().add(transaction);
        }
        CustomerAccount customerAccount = accountRepository.save(account);
        log.info("Customer account created with account number: {} ", customerAccount.getId());
        return CustomerAccountDto.builder()
                .id(customerAccount.getId())
                .initialCredit(customerAccount.getInitialCredit())
                .transactions(customerAccount.getTransactions())
                .customer(customerAccount.getCustomer())
                .startDate(customerAccount.getStartDate())
                .build();
    }

    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
