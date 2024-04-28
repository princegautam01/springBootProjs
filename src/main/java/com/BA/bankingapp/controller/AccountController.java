package com.BA.bankingapp.controller;

import com.BA.bankingapp.dto.AccountDto;
import com.BA.bankingapp.service.accountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private accountService AccountService;

    public AccountController(accountService AccountService)
    {
        this.AccountService = AccountService;
    }

    // Add Account REST Api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(AccountService.createAccount(accountDto) , HttpStatus.CREATED);
    }

    // Get Account REST Api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        AccountDto accountDto = AccountService.getAccount(id);
        return ResponseEntity.ok(accountDto);
    }
    //Deposit REST Api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id , @RequestBody Map<String,Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = AccountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    // WithDraw REST Api
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id , @RequestBody Map<String,Double>request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = AccountService.withDraw(id , amount);
        return ResponseEntity.ok(accountDto);
    }
   // GetAllAccount REST Api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accounts = AccountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    //DeleteAccount REST Api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        AccountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully!");
    }

}
