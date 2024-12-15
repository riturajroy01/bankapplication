package org.banking.bankaccount.domain.dto;

import lombok.Builder;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.Set;


@Builder
public record CustomerAccountDto( Long id, BigDecimal initialCredit, String startDate, Customer customer, Set<AccountTransaction> transactions ) {
}
