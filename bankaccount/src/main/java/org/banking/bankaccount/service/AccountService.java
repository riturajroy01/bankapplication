package org.banking.bankaccount.service;


import lombok.extern.slf4j.Slf4j;
import org.banking.bankaccount.domain.CreateAccountRequest;
import org.banking.bankaccount.domain.entity.CustomerAccount;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.exception.NotFoundException;
import org.banking.bankaccount.repository.AccountRepository;
import org.banking.bankaccount.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Slf4j
@Service
public class AccountService {

    CustomerRepository customerRepository;
    AccountRepository accountRepository;

    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }
    @Transactional
    public String createAccount(CreateAccountRequest createAccountRequest){

        Customer customer = customerRepository.findById(createAccountRequest.customerID())
                        .orElseThrow(()->
                                new NotFoundException("Customer not found with id customer id "+createAccountRequest.customerID()));
        CustomerAccount account = new CustomerAccount(createAccountRequest.initialCredit(), getCurrentDate(),customer, new HashSet<>());

        if (createAccountRequest.initialCredit().compareTo(BigDecimal.ZERO) > 0) {
            AccountTransaction transaction = new AccountTransaction(createAccountRequest.initialCredit(),getCurrentDate(),account);
            account.getTransactions().add(transaction);
        }

        CustomerAccount customerAccount = accountRepository.save(account);
        log.info("Customer account created with account number: {} ",customerAccount.getId());
         return "Account has been created with account id "+customerAccount.getId() +" with credit $"+customerAccount.getInitialCredit();
    }

    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

  /*  @Transactional(readOnly = true)
    public CustomerDto getCustomerDetails(Long customerId){
        Customer c =  customerRepository.findCustomersById(customerId);
       CustomerDto customerDto = new CustomerDto();
       customerDto.setId(c.getId());
       customerDto.setName(c.getName());
       customerDto.setAccount(c.getAccount());
        return customerDto;
    }*/
}
