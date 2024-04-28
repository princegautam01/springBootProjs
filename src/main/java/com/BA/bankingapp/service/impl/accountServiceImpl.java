package com.BA.bankingapp.service.impl;

import com.BA.bankingapp.dto.AccountDto;
import com.BA.bankingapp.entity.account;
import com.BA.bankingapp.mapper.accountMapper;
import com.BA.bankingapp.repository.AccountRepository;
import com.BA.bankingapp.service.accountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class accountServiceImpl implements accountService
{


    private AccountRepository accountRepository;
    public accountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        account acc = accountMapper.mapToAccount(accountDto);
        account savedAccount = accountRepository.save(acc);

        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccount(Long Id)
    {
        account acc = accountRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));
        return accountMapper.mapToAccountDto(acc);
    }

    @Override
    public AccountDto deposit(Long id, double amt) {
        account acc = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

       double total = acc.getBalance()+amt;
       acc.setBalance(total);
       account savedAccount = accountRepository.save(acc);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withDraw(Long id, double amt)
    {
        account acc = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));
        if(acc.getBalance()<amt)
            throw new RuntimeException("Insufficient Funds");

        double total = acc.getBalance()-amt;
        acc.setBalance(total);
        account savedAccount = accountRepository.save(acc);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<account> Accounts = accountRepository.findAll();

        return Accounts.stream().map((account) ->accountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        account acc = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));
        accountRepository.deleteById(id);
    }
}
