package org.banking.banktransaction.controller;

import org.banking.bankaccount.domain.CreateAccountRequest;
import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.service.AccountService;
import org.banking.bankaccount.service.CustomerService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Controller
public class BankUIController {

   private final CustomerService customerService;
   private final AccountService accountService;

    public BankUIController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/customer/create-account/{customerId}")
    public String createAccountForm(Model model, @PathVariable("customerId") String customerId) {
       // log.info("Account create request for customer:{}", customerId);
        System.out.println("customerId"+customerId);

        CustomerDto customerDto = customerService.getCustomerDetails(Long.parseLong(customerId));
        model.addAttribute("customer", customerDto);
        model.addAttribute("initialCredit", BigDecimal.ZERO);
        return "create-account-form";
    }

    @PostMapping("/customer/account/createAccount")
    public String customerAccountCreate(@ModelAttribute CreateAccountRequest createAccountRequest, Model model) throws ChangeSetPersister.NotFoundException {

        System.out.println(createAccountRequest);
        //model.addAttribute("account", createAccountRequest);
        accountService.createAccount(createAccountRequest);

        return "redirect:/customer/account/profile/"+createAccountRequest.getCustomerID();
    }

    @RequestMapping(value = "/customer/account/profile/{customerId}", method = RequestMethod.GET)
    public String customerProfile(Model model, @PathVariable("customerId") String customerId) {
        //code here , connect db and get data and send value to html like below
        CustomerDto customerDto = customerService.getCustomerDetails(Long.parseLong(customerId));
        model.addAttribute("customeraccountdetails", customerDto);
        return "customer-profile";
    }
}
