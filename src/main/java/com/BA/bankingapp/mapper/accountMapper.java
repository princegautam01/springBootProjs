package com.BA.bankingapp.mapper;

import com.BA.bankingapp.dto.AccountDto;
import com.BA.bankingapp.entity.account;

public class accountMapper
{
    public static account mapToAccount(AccountDto accountDto)
    {
        account acc = new account(accountDto.getId(),
                accountDto.getAccountName(),
                accountDto.getBalance()
        );
       return acc;
    }

    public static AccountDto mapToAccountDto(account account)
    {
        AccountDto ad = new AccountDto(account.getId(),
                account.getAccountName(),
                account.getBalance()
        );
        return ad;
    }
}
