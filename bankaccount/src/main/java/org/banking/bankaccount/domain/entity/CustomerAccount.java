package org.banking.bankaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
/*@Table(name = "account")*/
public class CustomerAccount {

    @Id

   /* @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")*/
    private String id;

    @Column(nullable = false)
    private BigDecimal initialCredit = BigDecimal.ZERO;

  /*  @Column(nullable = false)
    private LocalDateTime startDate;*/


    @Column
      private String startDate;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)*/
    @ManyToOne(/*fetch = FetchType.EAGER,*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   @OneToMany(mappedBy = "account", /*fetch = FetchType.EAGER,*/ cascade = CascadeType.ALL)
    private List<AccountTransaction> transactions;

    public CustomerAccount() {
    }

    public CustomerAccount(BigDecimal initialCredit, String startDate, Customer customer) {

        this.initialCredit = initialCredit;
        this.startDate = startDate;
        this.customer = customer;
    }

    public CustomerAccount(String id, BigDecimal initialCredit, String startDate, Customer customer, List<AccountTransaction> transactions) {
        this.id = id;
        this.initialCredit = initialCredit;
        this.startDate = startDate;
        this.customer = customer;
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(BigDecimal initialCredit) {
        this.initialCredit = initialCredit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<AccountTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<AccountTransaction> transactions) {
        this.transactions = transactions;
    }
}
