package com.example.bank_system.Controller;

import com.example.bank_system.Api.ApiResponse;
import com.example.bank_system.DTO.AccountIDTO;
import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/get-accounts")
    public ResponseEntity getAllAccounts(@AuthenticationPrincipal MyUser user) {
       return ResponseEntity.status(200).body(accountService.getAllAccounts(user.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity createAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid AccountIDTO account) {
        accountService.createAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created successfully"));
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId, @RequestBody @Valid Account account) {
        accountService.updateAccount(user.getId(), accountId, account);
        return ResponseEntity.status(200).body(new ApiResponse("Account updated successfully"));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        accountService.deleteAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted successfully"));
    }

    @GetMapping("/get-account/{accountId}")
    public ResponseEntity getAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.getAccount(user.getId(), accountId));
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId, @PathVariable Integer amount) {
        accountService.deposit(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Deposit successfully"));
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId, @PathVariable Integer amount) {
        accountService.withdraw(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Withdraw successfully"));
    }

    @PutMapping("/transfer/{accountId1}/{accountId2}/{amount}")
    public ResponseEntity transferMoney(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId1, @PathVariable Integer accountId2, @PathVariable Integer amount) {
        accountService.transferMoney(user.getId(), accountId1, accountId2, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer successfully"));
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        accountService.blockAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked successfully"));
    }

    @PutMapping("/active/{accountId}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        accountService.activateAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated successfully"));
    }
}
