package org.banking.bankaccount.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID")
   @SequenceGenerator(sequenceName = "ACCOUNT_ID_SEQ", initialValue = 1000000000, allocationSize = 1, name = "ACCOUNT_ID")
    private Long id;

    @Column(nullable = false)
    private BigDecimal initialCredit = BigDecimal.ZERO;

    @Column(nullable = false)
    private String startDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonManagedReference
   @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AccountTransaction> transactions;

}
