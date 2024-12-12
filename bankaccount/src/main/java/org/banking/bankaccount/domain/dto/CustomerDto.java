package org.banking.bankaccount.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banking.bankaccount.domain.entity.CustomerAccount;


import java.util.Set;



public class CustomerDto {

    private Long id;

    private String name;

    private String surname;

    private Set<CustomerAccount> account;

    public CustomerDto(Long id, String name, String surname, Set<CustomerAccount> account) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.account = account;
    }

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<CustomerAccount> getAccount() {
        return account;
    }

    public void setAccount(Set<CustomerAccount> account) {
        this.account = account;
    }
}
