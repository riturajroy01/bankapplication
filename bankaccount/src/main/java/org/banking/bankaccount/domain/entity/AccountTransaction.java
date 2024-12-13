package org.banking.bankaccount.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;



@Entity
@Table(name = "TRANSACTION")
public class AccountTransaction {

    @Id
  /*  @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

 /*   @Column(nullable = false)
    private LocalDateTime transactionDate;*/

    public AccountTransaction() {
    }

    @Column
    private String transactionDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    CustomerAccount account;

    public AccountTransaction(BigDecimal amount, String transactionDate, CustomerAccount account) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

/*    public AccountTransaction(String id, BigDecimal amount, String transactionDate, CustomerAccount account) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
