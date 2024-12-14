package org.banking.bankaccount.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.domain.entity.CustomerAccount;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    private String name;

    private String surname;

    private Set<CustomerAccount> account;

    private Set<AccountTransaction> transactions;

}
