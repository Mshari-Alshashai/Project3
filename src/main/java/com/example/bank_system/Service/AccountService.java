package com.example.bank_system.Service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.DTO.AccountIDTO;
import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.AccountRepository;
import com.example.bank_system.Repository.AuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;

    public List<Account> getAllAccounts(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (userId == null) throw new ApiException("user not found");
        return accountRepository.findAccountsByCustomer(user.getCustomer());
    }

    public void createAccount(Integer userId, AccountIDTO accountIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Account account = new Account();

        account.setAccountNumber(accountIDTO.getAccountNumber());
        account.setBalance(accountIDTO.getBalance());
        account.setCustomer(user.getCustomer());

        accountRepository.save(account);
    }

    public void updateAccount(Integer userId,Integer accountId,Account account) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Account oldAccount= accountRepository.findAccountById(accountId);
        if(oldAccount == null) throw new ApiException("account not found");

        oldAccount.setBalance(account.getBalance());
        accountRepository.save(oldAccount);
    }

    public void deleteAccount(Integer userId,Integer accountId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Account oldAccount= accountRepository.findAccountById(accountId);
        if(oldAccount == null) throw new ApiException("account not found");

        accountRepository.delete(oldAccount);
    }


    public Account getAccount(Integer userId,Integer accountId) {
        MyUser user = authRepository.findMyUserById(userId);
        Account account= accountRepository.findAccountById(accountId);
        if(user == null) throw new ApiException("user not found");
        if (!account.isActive()) throw new ApiException("account not active");

        return accountRepository.findAccountById(accountId);
    }

    public void deposit(Integer userId, Integer accountId, Integer amount) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Account account= accountRepository.findAccountById(accountId);
        if(account == null) throw new ApiException("account not found");
        if (!account.isActive()) throw new ApiException("account not active");

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);
    }

    public void withdraw(Integer userId, Integer accountId, Integer amount) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Account account= accountRepository.findAccountById(accountId);
        if(account == null) throw new ApiException("account not found");
        if (!account.isActive()) throw new ApiException("account not active");

        if (account.getBalance() - amount < 0) throw new ApiException("not enough money");

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);
    }

    public void transferMoney(Integer userId, Integer accountId1, Integer accountId2, Integer amount) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Account account1= accountRepository.findAccountById(accountId1);
        Account account2= accountRepository.findAccountById(accountId2);
        if(account1 == null) throw new ApiException("account not found");
        if(account2 == null) throw new ApiException("account not found");
        if (!account1.isActive()) throw new ApiException("first account not active");
        if (!account2.isActive()) throw new ApiException("second account not active");

        if (account1.getBalance() - amount < 0) throw new ApiException("not enough money");
        account1.setBalance(account1.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);

        accountRepository.save(account1);
        accountRepository.save(account2);
    }

    public void blockAccount(Integer userId, Integer accountId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        Account account= accountRepository.findAccountById(accountId);
        if(account == null) throw new ApiException("account not found");
        account.setActive(false);
        accountRepository.save(account);
    }

    @Transactional
    public void activateAccount(Integer userId, Integer accountId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("User not found");

        Account account = accountRepository.findAccountById(accountId);
        if (account == null) throw new ApiException("Account not found");
        if (account.isActive()) throw new ApiException("Account already active");

        accountRepository.activateAccount(accountId);
    }

}
