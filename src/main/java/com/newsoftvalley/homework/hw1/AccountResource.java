package com.newsoftvalley.homework.hw1;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AccountResource {
    Long _id = 0l;
    Map<Long, Account> _idToAccountMap = new HashMap<>();
    public Long add(Account account){
        _idToAccountMap.put(_id, account);
        _id++;
        return _id - 1;
    }

    public Account get(Long id) throws AccountNotFoundException {
        Account account = _idToAccountMap.get(id);
        if (account == null) {
            throw new AccountNotFoundException("Account Not Found");
        }
        return account;
    }

    public boolean delete(Long id) throws AccountNotFoundException {
        if (_idToAccountMap.get(id) != null){
            _idToAccountMap.remove(id);
            return true;
        } else {
            throw new AccountNotFoundException();
        }
    }

    public Account update(Long id, Account account) throws AccountNotFoundException {
        if (_idToAccountMap.get(id) != null) {
            _idToAccountMap.put(id, account);
            return _idToAccountMap.get(id);
        } else {
            throw new AccountNotFoundException();
        }
    }

}
