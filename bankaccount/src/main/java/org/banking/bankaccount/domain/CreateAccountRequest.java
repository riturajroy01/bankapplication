package org.banking.bankaccount.domain;


import java.math.BigDecimal;


public record CreateAccountRequest( String customerID, BigDecimal initialCredit) {
}
