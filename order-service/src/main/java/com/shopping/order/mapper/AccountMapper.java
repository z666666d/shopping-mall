package com.shopping.order.mapper;

import com.shopping.entities.account.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper{

    /**
     * 从本地账户表中查询出所有账户信息
     * @return
     */
    List<Account> getAccount();

    Account getAccountById(String acctNo);

    void insert(Account account);

    void updateById(Account account);

    void deleteById(String acctNo);
}
