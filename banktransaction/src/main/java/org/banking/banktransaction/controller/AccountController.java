package org.banking.banktransaction.controller;



import org.banking.bankaccount.service.AccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
}
