package org.banking.bankaccount.domain.dto;


import lombok.Builder;
import org.banking.bankaccount.domain.entity.CustomerAccount;


import java.util.Set;

@Builder
public class CustomerDto {

    private String id;

    private String name;

    private String surname;

    private Set<CustomerAccount> account;
}
