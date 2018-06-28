package com.agree.gf.limit.dao;

import com.agree.base.redis.JedisReadPoolUtil;
import com.agree.base.redis.JedisWritePoolUtil;
import com.agree.gf.limit.bean.AifAccountBean;

import java.util.HashMap;
import java.util.Map;

public class AifAccountDao {

    public static String jsetAifAccountBean(AifAccountBean bean){
        String key = "";
        String rs = "";
        Map<String,String> map = new HashMap<String,String>();

        key = "A_"+bean.getAccountNo();
        map.put("aif_normal_single",bean.getAif_normal_single());
        map.put("aif_normal_day",bean.getAif_normal_day());
        map.put("aif_normal_month",bean.getAif_normal_month());
        map.put("aif_large_single",bean.getAif_large_single());
        map.put("aif_large_day",bean.getAif_large_day());
        map.put("aif_large_month",bean.getAif_large_month());

        rs = JedisReadPoolUtil.getInstance().hash().hmset(key,map);

        return rs;
    }




}
