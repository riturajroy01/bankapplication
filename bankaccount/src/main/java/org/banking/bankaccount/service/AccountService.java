package org.banking.bankaccount.service;



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
import java.util.HashSet;

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


        CustomerAccount account = new CustomerAccount(createAccountRequest.getInitialCredit(), "",customer, new HashSet<>());
       // account.setCustomer(customer);
       // account.setInitialCredit(createAccountRequest.getInitialCredit());
        //account.setStartDate(LocalDateTime.now());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            AccountTransaction transaction = new AccountTransaction(createAccountRequest.getInitialCredit(),"",account);
           // transaction.setAmount(createAccountRequest.getInitialCredit());
            //transaction.setTransactionDate(LocalDateTime.now());
            //transaction.setAccount(account);

            account.getTransactions().add(transaction);
            //account.setTransactions(new HashSet<>(transaction));

        }

        CustomerAccount customerAccount = accountRepository.save(account);
         return "Account has been created with account id "+customerAccount.getId() +" with credit "+customerAccount.getInitialCredit();

    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerDetails(Long customerId) throws ChangeSetPersister.NotFoundException {
        System.out.println("customerId"+customerId);


        Customer c =  customerRepository.findCustomersById(customerId);
       CustomerDto customerDto = new CustomerDto();
       customerDto.setId(c.getId());
       customerDto.setName(c.getName());
       customerDto.setAccount(c.getAccount());
        return customerDto;
    }
}
