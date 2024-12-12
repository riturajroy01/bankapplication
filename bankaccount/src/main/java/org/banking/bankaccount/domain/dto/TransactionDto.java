package org.banking.bankaccount.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class TransactionDto {

    private String id;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

}
