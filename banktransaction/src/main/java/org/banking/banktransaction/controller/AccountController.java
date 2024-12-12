package org.banking.banktransaction.controller;


import org.banking.bankaccount.domain.CreateAccountRequest;
import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.service.AccountService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest createAccountRequest) throws ChangeSetPersister.NotFoundException {

        return new ResponseEntity<>(accountService.createAccount(createAccountRequest), HttpStatus.CREATED);

    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable("customerId") String customerId) throws ChangeSetPersister.NotFoundException {

        return new ResponseEntity<>(accountService.getCustomerDetails(Long.parseLong(customerId)), HttpStatus.OK);

    }

}
