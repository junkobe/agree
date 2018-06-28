package com.agree.gf.limit.dao;

import com.agree.base.StringUtil;
import com.agree.base.redis.JedisPoolUtil;
import com.agree.base.redis.JedisReadPoolUtil;
import com.agree.base.redis.JedisWritePoolUtil;
import com.agree.gf.limit.bean.InitLimitBean;
import com.agree.gf.limit.cfg.CONFIGS;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitLimitDao {

    static String REDIS_KEY="limit.initlimit";
//    public final static int FIELD_INITVAL = 0;
//    public final static int FIELD_RETYPE = 1;
//    public final static int FIELD_SERNO = 2;
//    public final static int FIELD_UPDATEDATE = 3;
//    public final static int FIELD_UPDATETIME = 4;

    private final static int keysNum = 150000;

//    public static String delInitLimitAll(){
//        long rs = 0L;
//        rs = JedisPoolUtil.getInstance().keys().del(REDIS_KEY);
//        if(rs <= 0)
//            return "";
//        else
//            return "OK";
//    }


    public static String jsetInitLimit(InitLimitBean bean){
        String rs = "";
        String type = bean.getType();
        String accountNo = bean.getAccountNo();
        String retype = bean.getRetype();

        String initKey = jgetInitValKey(type,accountNo,retype);
//        String retypeKey = jgetRetypeKey(type,accountNo,retype);
//        String sernoKey = jgetSernoKey(type,accountNo,retype);
//        String dateKey = jgetUpdatedateKey(type,accountNo,retype);
//        String timeKey = jgetUpdatetimeKey(type,accountNo,retype);

        String initVal = bean.getInitval();
//        String retypeVal = bean.getRetype();
//        String sernoVal = bean.getBusi_serno();
//        String dateVal = bean.getUpdatedate();
//        String timeVal = bean.getUpdatetime();

        rs = JedisWritePoolUtil.getInstance()
                .strings().set(initKey,initVal);

//        rs = JedisWritePoolUtil.getInstance()
//                               .strings().mset(initKey,initVal,
//                                               retypeKey,retypeVal,
//                                               sernoKey,sernoVal,
//                                               dateKey,dateVal,
//                                               timeKey,timeVal);

        return rs;
    }

    public static String jhsetInitLimit(InitLimitBean bean){
        String rs = "";
        long r = 0;
        String type = bean.getType();
        String accountNo = bean.getAccountNo();
        String retype = bean.getRetype();
        ArrayList<String> fields = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();

        fields.add(jgetInitValKey(type,accountNo,retype));
        values.add(bean.getInitval());

        fields.add(jgetRetypeKey(type,accountNo,retype));
        values.add(bean.getRetype());

        fields.add(jgetSernoKey(type,accountNo,retype));
        values.add(bean.getBusi_serno());

        fields.add(jgetUpdatedateKey(type,accountNo,retype));
        values.add(bean.getUpdatedate());

        fields.add(jgetUpdatetimeKey(type,accountNo,retype));
        values.add(bean.getUpdatetime());

        r = JedisWritePoolUtil.getInstance().hash().hset(REDIS_KEY,fields.toArray(new String[0]),values.toArray(new String[0]));

        if(r == 1){
            return "OK";
        }else{
            return "";
        }

    }


    public static String jhmsetInitLimitBean(InitLimitBean bean){
        String rs = "";
        Map<String,String> initlimitMap = new HashMap<String,String>();
        String type = bean.getType();
        String accountNo = bean.getAccountNo();
        String retype = bean.getRetype();
        initlimitMap.put(jgetInitValKey(type,accountNo,retype),bean.getInitval());
//        initlimitMap.put(jgetRetypeKey(type,accountNo,retype),bean.getRetype());
//         initlimitMap.put(jgetSernoKey(type,accountNo,retype),bean.getBusi_serno());
////        initlimitMap.put(jgetUpdatedateKey(type,accountNo,retype),bean.getUpdatedate());
////       initlimitMap.put(jgetUpdatetimeKey(type,accountNo,retype),bean.getUpdatetime());

        rs = JedisWritePoolUtil.getInstance().hash().hmset(REDIS_KEY,initlimitMap);
        return rs;
    }



    public static String jhmsetAllInitBean(List<InitLimitBean> beans){
        String rs = "";
        Map<String,String> initlimitMap = new HashMap<>();
        for(InitLimitBean bean:beans){
            String type = bean.getType();
            String accountNo = bean.getAccountNo();
            String retype = bean.getRetype();
            initlimitMap.put(jgetInitValKey(type,accountNo,retype),bean.getInitval());
        }
        rs = JedisWritePoolUtil.getInstance().hash().hmset(REDIS_KEY,initlimitMap);
        beans.clear();
        return rs;
    }

    public static String jhmsetMultKeyInitLimitBean(InitLimitBean bean){
        String rs = "";
        Map<String,String> initlimitMap = new HashMap<String,String>();
        String type = bean.getType();
        String accountNo = bean.getAccountNo();
        String retype = bean.getRetype();
        initlimitMap.put(jgetInitValKey(type,accountNo,retype),bean.getInitval());

        long num = Long.parseLong(accountNo);
        num = num%keysNum;

        String key = REDIS_KEY+CONFIGS.spileVal+String.format("%05d",num);

        rs = JedisWritePoolUtil.getInstance().hash().hmset(key,initlimitMap);

        return rs;
    }


    public static String jhmsetOnekeyInitLimitBean(List<InitLimitBean> beans){
        String rs = "";
        Map<String,String> initlimitMap = new HashMap<String,String>();
//        Map<String,Map<String,String>> sameKeyMap = new HashMap<>();
        String accountNo = "";
        for (InitLimitBean bean : beans) {
            String type = bean.getType();
            accountNo = bean.getAccountNo();
            String retype = bean.getRetype();
            initlimitMap.put(jgetInitValKey(type,accountNo,retype),bean.getInitval());

        }

        rs = JedisWritePoolUtil.getInstance().hash().hmset(REDIS_KEY,initlimitMap);
        return  rs;
    }

    public static String jhmsetMultKeyInitLimitBean(List<InitLimitBean> beans){
        String rs = "";
        Map<String,String> initlimitMap = new HashMap<String,String>();
//        Map<String,Map<String,String>> sameKeyMap = new HashMap<>();
        String accountNo = "";
        for (InitLimitBean bean : beans) {
            String type = bean.getType();
            accountNo = bean.getAccountNo();
            String retype = bean.getRetype();
            initlimitMap.put(jgetInitValKey(type,accountNo,retype),bean.getInitval());

        }

        long num = Long.parseLong(accountNo);
        num = num%keysNum;
        String key = REDIS_KEY+CONFIGS.spileVal+String.format("%05d",num);


//        rs = JedisWritePoolUtil.getInstance().hash().hmset(key,initlimitMap);
        rs = JedisWritePoolUtil.getInstance().hash().hmset(key,initlimitMap);
        return  rs;
    }


    public static void jpipeline(List<InitLimitBean> beans){
        String rs = "";
        Map<String,Map<String,String>> sameKeyMap = new HashMap<>();

        for(InitLimitBean bean : beans){
            Map<String,String> initlimitMap = new HashMap<String,String>();
            String type = bean.getType();
            String accountNo = bean.getAccountNo();
            String retype = bean.getRetype();
            initlimitMap.put(jgetInitValKey(type,accountNo,retype),bean.getInitval());

            long num = Long.parseLong(accountNo);
            num = num%keysNum;
            String key = REDIS_KEY+CONFIGS.spileVal+String.format("%05d",num);

            Map<String,String> map = sameKeyMap.get(key);
            if(map==null){
                sameKeyMap.put(key,initlimitMap);
            }else{
                map.putAll(initlimitMap);
            }

        }
        JedisWritePoolUtil.getInstance().pipe().hmset(sameKeyMap);
        beans.clear();
//        return rs;

    }


    private static String jgetInitValKey(String type,String accountNo,String retype){
        return StringUtil.appendStrList(
                type,
                CONFIGS.spileVal,
                accountNo,
                CONFIGS.spileVal,
                retype,
                CONFIGS.spileVal,
                "initval"
                );
    }


    private static String jgetRetypeKey(String type,String accountNo,String retype){
        return StringUtil.appendStrList(
                type,
                CONFIGS.spileVal,
                accountNo,
                CONFIGS.spileVal,
                retype,
                CONFIGS.spileVal,
                "retype"
        );
    }

    private static String jgetSernoKey(String type,String accountNo,String retype){
        return StringUtil.appendStrList(
                type,
                CONFIGS.spileVal,
                accountNo,
                CONFIGS.spileVal,
                retype,
                CONFIGS.spileVal,
                "busi_serno"
        );
    }



    private static String jgetUpdatedateKey(String type,String accountNo,String retype){
        return StringUtil.appendStrList(
                type,
                CONFIGS.spileVal,
                accountNo,
                CONFIGS.spileVal,
                retype,
                CONFIGS.spileVal,
                "updatedate"
        );
    }

    private static String jgetUpdatetimeKey(String type,String accountNo,String retype){
        return StringUtil.appendStrList(
                type,
                CONFIGS.spileVal,
                accountNo,
                CONFIGS.spileVal,
                retype,
                CONFIGS.spileVal,
                "updatetime"
        );
    }


//    public static void main(String args[]){
//        String num = "000000000000000000000123";
//        System.out.println(Long.parseLong(num));
//    }

}
