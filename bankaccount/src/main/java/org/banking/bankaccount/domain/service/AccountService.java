package org.banking.bankaccount.domain.service;



import org.banking.bankaccount.domain.CreateAccountRequest;
import org.banking.bankaccount.domain.entity.CustomerAccount;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.domain.repository.AccountRepository;
import org.banking.bankaccount.domain.repository.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

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


        CustomerAccount account = new CustomerAccount(UUID.randomUUID().toString(),createAccountRequest.getInitialCredit(), "",customer, new ArrayList<>());
       // account.setCustomer(customer);
       // account.setInitialCredit(createAccountRequest.getInitialCredit());
        //account.setStartDate(LocalDateTime.now());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            AccountTransaction transaction = new AccountTransaction(UUID.randomUUID().toString(),createAccountRequest.getInitialCredit(),"",account);
           // transaction.setAmount(createAccountRequest.getInitialCredit());
            //transaction.setTransactionDate(LocalDateTime.now());
            //transaction.setAccount(account);

            account.getTransactions().add(transaction);
            //account.setTransactions(new HashSet<>(transaction));

        }

        CustomerAccount customerAccount = accountRepository.save(account);
         return "Account has been created with account id "+customerAccount.getId() +" with credit "+customerAccount.getInitialCredit();

    }
}
