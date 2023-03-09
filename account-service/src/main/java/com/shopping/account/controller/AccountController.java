package com.shopping.account.controller;

import com.shopping.entities.account.Account;
import com.shopping.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/account/add")
    public void addAccount(@RequestBody Account account){
        accountService.addAccount(account);
    }
}
