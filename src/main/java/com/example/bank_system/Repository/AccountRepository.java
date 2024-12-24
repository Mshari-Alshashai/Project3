package com.example.bank_system.Repository;

import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAccountsByCustomer(Customer customer);
    Account findAccountById(Integer id);

    @Modifying
    @Query("UPDATE Account a SET a.isActive = true WHERE a.id = :accountId")
    void activateAccount(@Param("accountId") Integer accountId);

}
