package org.banking.bankaccount.service;

import org.banking.bankaccount.domain.dto.CreateAccountRequest;
import org.banking.bankaccount.domain.dto.CustomerAccountDto;
import org.banking.bankaccount.exception.NotFoundException;
import org.banking.bankaccount.repository.AccountRepository;
import org.banking.bankaccount.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccountWithNoInitialCreditTest() {

        Mockito.when(customerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(TestUtil.buildCustomer(false, BigDecimal.ZERO)));

        Mockito.when(accountRepository.save(ArgumentMatchers.any()))
                .thenReturn(TestUtil.buildCustomerAccount(false, BigDecimal.ZERO));
        CustomerAccountDto customerAccountDto = accountService.createAccount(new CreateAccountRequest("1", BigDecimal.ZERO));
        Assertions.assertNotNull(customerAccountDto);
        Assertions.assertEquals(1L, customerAccountDto.id());
        Assertions.assertEquals(0, customerAccountDto.transactions().size());
    }

    @Test
    void createAccountWithInitialCreditTest() {

        var amount = new BigDecimal(400);
        Mockito.when(customerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(TestUtil.buildCustomer(true, amount)));
        Mockito.when(accountRepository.save(ArgumentMatchers.any()))
                .thenReturn(TestUtil.buildCustomerAccount(true, amount));
        CustomerAccountDto customerAccountDto = accountService.createAccount(new CreateAccountRequest("1", amount));
        Assertions.assertNotNull(customerAccountDto);
        Assertions.assertEquals(1L, customerAccountDto.id());
        Assertions.assertNotNull(customerAccountDto.transactions());

    }

    @Test
    void whenExceptionThrown_createAccountTest() {
        Mockito.when(customerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());
        Exception exception = assertThrows(NotFoundException.class, () -> accountService.createAccount(new CreateAccountRequest("1", BigDecimal.ZERO)));
        String expectedMessage = "Customer not found with id customer id 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
