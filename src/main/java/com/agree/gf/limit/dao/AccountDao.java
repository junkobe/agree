package com.agree.gf.limit.dao;

import com.agree.base.redis.JedisWritePoolUtil;

public class AccountDao {

    public static boolean exist(String key,String account){
        boolean rs = false;
        rs = JedisWritePoolUtil.getInstance().sets().sismember(key,account);
        return rs;
    }

}
