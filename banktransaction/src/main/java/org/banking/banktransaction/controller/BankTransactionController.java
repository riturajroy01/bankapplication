package org.banking.banktransaction.controller;

import lombok.extern.slf4j.Slf4j;
import org.banking.bankaccount.domain.dto.CreateAccountRequest;
import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.service.AccountService;
import org.banking.bankaccount.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@Controller
public class BankTransactionController {

   private final CustomerService customerService;
   private final AccountService accountService;

    public BankTransactionController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/customer/create-account/{customerId}")
    public String createAccountForm(Model model, @PathVariable("customerId") String customerId) {
       log.info("Account create request form for customer:{}", customerId);
        CustomerDto customerDto = customerService.getCustomerDetails(Long.parseLong(customerId));
        model.addAttribute("customer", customerDto);
        model.addAttribute("initialCredit", BigDecimal.ZERO);
        return "create-account-form";
    }

    @PostMapping("/customer/account/createAccount")
    public String customerAccountCreate(@ModelAttribute CreateAccountRequest createAccountRequest, Model model) {
        log.info("Account create request submitted for customer:{} with initial credit amount {}",
                createAccountRequest.customerID(), createAccountRequest.initialCredit());
        accountService.createAccount(createAccountRequest);
        return "redirect:/customer/account/profile/"+createAccountRequest.customerID();
    }

    @RequestMapping(value = "/customer/account/profile/{customerId}", method = RequestMethod.GET)
    public String customerProfile(Model model, @PathVariable("customerId") String customerId) {
        CustomerDto customerDto = customerService.getCustomerDetails(Long.parseLong(customerId));
        model.addAttribute("customeraccountdetails", customerDto);
        return "customer-profile";
    }
}
