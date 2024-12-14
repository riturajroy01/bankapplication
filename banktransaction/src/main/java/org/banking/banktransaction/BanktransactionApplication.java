package org.banking.banktransaction;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"org.banking.bankaccount.*","org.banking.banktransaction.*"})
@EntityScan("org.banking.bankaccount.*")
@EnableJpaRepositories("org.banking.bankaccount.*")
public class BanktransactionApplication {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BanktransactionApplication.class, args);
    }

    @PostConstruct
    private void initDb() {
        String[] sqlStatements = {
                "insert into \"customer\"(\"name\", \"surname\") values('Bob','Schalp')",
                "insert into \"customer\"(\"name\", \"surname\") values('Robin','Hood')"

        };
        Arrays.asList(sqlStatements).forEach(sql -> jdbcTemplate.execute(sql));
    }

}
