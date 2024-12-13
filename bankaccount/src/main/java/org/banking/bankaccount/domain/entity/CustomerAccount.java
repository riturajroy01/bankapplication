package org.banking.bankaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")*/
    private Long id;

    @Column(nullable = false)
    private BigDecimal initialCredit = BigDecimal.ZERO;

  /*  @Column(nullable = false)
    private LocalDateTime startDate;*/


    @Column
      private String startDate;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)*/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
   @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AccountTransaction> transactions;

    public CustomerAccount() {
    }

    public CustomerAccount(BigDecimal initialCredit, String startDate, Customer customer) {

        this.initialCredit = initialCredit;
        this.startDate = startDate;
        this.customer = customer;
    }

    public CustomerAccount(BigDecimal initialCredit, String startDate, Customer customer, Set<AccountTransaction> transactions) {

        this.initialCredit = initialCredit;
        this.startDate = startDate;
        this.customer = customer;
        this.transactions = transactions;
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

    public Set<AccountTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<AccountTransaction> transactions) {
        this.transactions = transactions;
    }
}
