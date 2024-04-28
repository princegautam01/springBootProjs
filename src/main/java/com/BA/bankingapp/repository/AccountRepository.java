package com.BA.bankingapp.repository;


import com.BA.bankingapp.entity.account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<account, Long>
{

}
