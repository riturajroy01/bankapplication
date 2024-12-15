package org.banking.bankaccount.domain.dto;


import java.math.BigDecimal;


public record CreateAccountRequest(String customerID, BigDecimal initialCredit) {
}
