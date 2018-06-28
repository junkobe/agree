package com.agree.gf.limit.services;

import com.agree.gf.limit.dao.AccountDao;

public class AccountService {

    public boolean exist(String key,String account){
        return AccountDao.exist(key,account);
    }
}
