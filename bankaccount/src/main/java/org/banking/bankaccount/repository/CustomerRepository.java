package org.banking.bankaccount.repository;



import org.banking.bankaccount.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, String> {


    //@Query(nativeQuery = true,value = "select s.account.transactions from \"customer\" s where s.id = :param")

   // @Query(nativeQuery = true,value = "select * from customer s, account a where s.id = a.customer_id and s.id = :param")

   // @Query(nativeQuery = true,value = "select s.account.transactions from customer s where s.id = :param")
  //  public Customer findCustomersById(@Param("param") String id);

    public Customer findCustomersById(Long id);
}
