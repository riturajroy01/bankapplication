package org.banking.bankaccount.domain.dto;

import lombok.Builder;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.AccountTransaction;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public class AccountDto {

    private Long id;

    private BigDecimal initialCredit;

    private LocalDateTime startDate;

    private Customer customer;

    private Set<AccountTransaction> transactions;
}
