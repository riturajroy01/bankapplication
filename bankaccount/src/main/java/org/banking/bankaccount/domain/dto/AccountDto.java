package org.banking.bankaccount.domain.dto;


import org.banking.bankaccount.domain.entity.Customer;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


public class AccountDto {

    private Long id;

    private BigDecimal initialCredit;

    private LocalDateTime startDate;

    private Customer customer;

    private Set<AccountTransaction> transactions;

    public AccountDto(Long id, BigDecimal initialCredit, LocalDateTime startDate, Customer customer, Set<AccountTransaction> transactions) {
        this.id = id;
        this.initialCredit = initialCredit;
        this.startDate = startDate;
        this.customer = customer;
        this.transactions = transactions;
    }

    public AccountDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(BigDecimal initialCredit) {
        this.initialCredit = initialCredit;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<AccountTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<AccountTransaction> transactions) {
        this.transactions = transactions;
    }
}
