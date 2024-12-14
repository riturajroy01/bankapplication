package org.banking.bankaccount.service;


import lombok.extern.slf4j.Slf4j;
import org.banking.bankaccount.domain.CreateAccountRequest;
import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.domain.entity.CustomerAccount;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.repository.AccountRepository;
import org.banking.bankaccount.repository.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

//@Slf4j
@Service
public class AccountService {

    CustomerRepository customerRepository;
    AccountRepository accountRepository;

    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }
    @Transactional
    public String createAccount(CreateAccountRequest createAccountRequest) throws ChangeSetPersister.NotFoundException {

        Customer customer = customerRepository.findById(createAccountRequest.getCustomerID())
                        .orElseThrow(
                                ChangeSetPersister.NotFoundException::new);
        CustomerAccount account = new CustomerAccount(createAccountRequest.getInitialCredit(), getCurrentDate(),customer, new HashSet<>());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            AccountTransaction transaction = new AccountTransaction(createAccountRequest.getInitialCredit(),getCurrentDate(),account);
            account.getTransactions().add(transaction);
        }

        CustomerAccount customerAccount = accountRepository.save(account);
        //log.info("Customer account created with account number: {} ",customerAccount.getId());
         return "Account has been created with account id "+customerAccount.getId() +" with credit "+customerAccount.getInitialCredit();
    }

    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerDetails(Long customerId) throws ChangeSetPersister.NotFoundException {
        Customer c =  customerRepository.findCustomersById(customerId);
       CustomerDto customerDto = new CustomerDto();
       customerDto.setId(c.getId());
       customerDto.setName(c.getName());
       customerDto.setAccount(c.getAccount());
        return customerDto;
    }
}
