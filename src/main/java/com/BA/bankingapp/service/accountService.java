package com.BA.bankingapp.service;

import com.BA.bankingapp.dto.AccountDto;
import jakarta.persistence.Id;

import java.util.List;

public interface accountService
{
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(Long Id);
    AccountDto deposit(Long id , double amt);
    AccountDto withDraw(Long id , double amt);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
