package org.banking.bankaccount.domain.dto;


import lombok.Builder;
import lombok.Data;
import org.banking.bankaccount.domain.entity.AccountTransaction;
import org.banking.bankaccount.domain.entity.CustomerAccount;

import java.util.List;
import java.util.Set;


@Data
@Builder
public class CustomerDto {

    private Long id;

    private String name;

    private String surname;

    private List<CustomerAccount> account;

    private Set<AccountTransaction> transactions;
}
