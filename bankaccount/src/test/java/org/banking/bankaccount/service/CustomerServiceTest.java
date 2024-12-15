package org.banking.bankaccount.service;

import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.exception.NotFoundException;
import org.banking.bankaccount.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
 class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void getCustomerDetailsTest() {

        var amount = new BigDecimal(400);
        Mockito.when(customerRepository.findCustomersById(ArgumentMatchers.any()))
                .thenReturn(TestUtil.buildCustomer(true, amount));
        CustomerDto customerDto = customerService.getCustomerDetails(1L);
        assertNotNull(customerDto);
        assertEquals(1L, customerDto.getId());
        assertNotNull(customerDto.getAccount());
        customerDto.getAccount().forEach(account -> assertNotNull(account.getTransactions()));

    }

    @Test
    void whenExceptionThrown_getCustomerDetailsTest() {
        Mockito.when(customerRepository.findCustomersById(ArgumentMatchers.any()))
                .thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> customerService.getCustomerDetails(1L));
        String expectedMessage = "Customer not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
