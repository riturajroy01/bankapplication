package org.banking.bankaccount.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;



@Entity
/*@NoArgsConstructor
@AllArgsConstructor*/
public class AccountTransaction {

    @Id
  /*  @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")*/
    private String id;

    @Column(nullable = false)
    private BigDecimal amount;

 /*   @Column(nullable = false)
    private LocalDateTime transactionDate;*/

    public AccountTransaction() {
    }

    @Column
    private String transactionDate;

    @ManyToOne(/*fetch = FetchType.EAGER,*/ optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    CustomerAccount account;

    public AccountTransaction(BigDecimal amount, String transactionDate, CustomerAccount account) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    public AccountTransaction(String id, BigDecimal amount, String transactionDate, CustomerAccount account) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public CustomerAccount getAccount() {
        return account;
    }

    public void setAccount(CustomerAccount account) {
        this.account = account;
    }
}
