package org.banking.bankaccount.service;

import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.CustomerAccount;

import java.math.BigDecimal;
import java.util.*;

public class TestUtil {

    static Customer buildCustomer(boolean transaction, BigDecimal initialCredit) {

        List<CustomerAccount> accountSet = new ArrayList<>();
        CustomerAccount customerAccount = buildCustomerAccount(transaction, initialCredit);
        if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
            AccountTransaction accountTransaction = AccountTransaction.builder()
                    .amount(initialCredit)
                    .account(customerAccount)
                    .transactionDate("2024-12-12 :12:00:00")
                    .build();
            customerAccount.getTransactions().add(accountTransaction);
        }
        accountSet.add(customerAccount);
        return Customer.builder()
                .id(1L)
                .name("TEST")
                .surname("TEST")
                .account(accountSet)
                .build();

    }

    static CustomerAccount buildCustomerAccount(boolean transaction, BigDecimal amount) {


        Set<AccountTransaction> transactions = new HashSet<>();
        transactions.add(AccountTransaction.builder().amount(amount).build());

        return !transaction ? CustomerAccount.builder()
                .id(1L)
                .startDate("2024-12-12 :12:00:00")
                .initialCredit(amount)
                .transactions(new HashSet<>())
                .build() :
                CustomerAccount.builder()
                        .id(1L)
                        .startDate("2024-12-12 :12:00:00")
                        .initialCredit(amount)
                        .transactions(transactions)
                        .build();

    }
}
