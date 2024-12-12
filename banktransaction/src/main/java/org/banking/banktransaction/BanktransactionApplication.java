package org.banking.banktransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.banking.bankaccount.*","org.banking.banktransaction.*"})
@EntityScan("org.banking.bankaccount.*")
@EnableJpaRepositories("org.banking.bankaccount.*")
public class BanktransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanktransactionApplication.class, args);
    }

}
