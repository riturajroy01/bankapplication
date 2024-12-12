package org.banking.banktransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.banking.bankaccount.service"})
public class BanktransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanktransactionApplication.class, args);
    }

}
