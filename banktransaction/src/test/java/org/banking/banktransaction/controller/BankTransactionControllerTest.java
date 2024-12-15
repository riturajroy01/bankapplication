package org.banking.banktransaction.controller;

import org.banking.bankaccount.domain.dto.CustomerDto;
import org.banking.bankaccount.service.AccountService;
import org.banking.bankaccount.service.CustomerService;
import org.banking.banktransaction.BankTransactionApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the class for WebMock with MockMvc Test
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BankTransactionApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
class BankTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private AccountService accountService;

    @Test
    void createAccountFormTest() throws Exception {
        when(customerService.getCustomerDetails(ArgumentMatchers.any()))
                .thenReturn(CustomerDto.builder().build());
        this.mockMvc.perform(get("/customer/create-account/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("BOB SCHALP")));
    }

}
