package com.shopping.account.mapper;

import com.shopping.entities.account.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    void insert(Account account);
}
