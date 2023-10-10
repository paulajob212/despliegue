package com.bank.accountSystem.controller;

import com.bank.accountSystem.dto.DepositRequest;
import com.bank.accountSystem.dto.TransferRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.model.Pocket;
import com.bank.accountSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //1.0 GET ALL ACCOUNTS
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    //1.1 GET POCKETS FROM EVERY ACCOUNT
    @GetMapping("/{accountNumber}/pockets")
    public ResponseEntity<List<Pocket>> getPocketsByAccountNumber(@PathVariable String accountNumber) {
        List<Pocket> pockets = accountService.getPocketsByAccountNumber(accountNumber);
        return ResponseEntity.ok(pockets);
    }

    //1.2 ACCOUNT OPENING
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        Account savedAccount = accountService.saveAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    //1.3 DEPOSIT INTO AN ACCOUNT
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<String> makeDeposit(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositRequest depositRequest) {
        String resultDeposit = accountService.deposit(accountNumber, depositRequest.getAmount());
        if (resultDeposit.equals("Account doesn´t exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDeposit);
        }
        return ResponseEntity.ok(resultDeposit);
    }

    //1.4 TRANSFER TO ANOTHER ACCOUNT
    @PostMapping("/transfer")
    public ResponseEntity<String> makeTransfer(@RequestBody TransferRequest transferRequest) {
        String resultTransfer = accountService.makeTransfer(transferRequest);
        if (resultTransfer.equals("One or both accounts do not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultTransfer);
        } else if (resultTransfer.equals("Insufficient balance in the source account")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultTransfer);
        }
        return ResponseEntity.ok(resultTransfer);
    }

    //1.5 CHECK ACCOUNT
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> findByAccountNumber(@PathVariable String accountNumber){
        Account account = accountService.findByAccountNumber(accountNumber);
        if (account == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    //1.6 DELETE ACCOUNT
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber){
        try{
            accountService.deleteAccountByAccNumber(accountNumber);
            return ResponseEntity.ok("Account successfully deleted");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
