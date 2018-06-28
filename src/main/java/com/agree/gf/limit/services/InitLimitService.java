package com.agree.gf.limit.services;

import com.agree.base.NumberUtil;
import com.agree.base.redis.JedisPoolUtil;
import com.agree.base.redis.JedisWritePoolUtil;
import com.agree.gf.limit.bean.AifAccountBean;
import com.agree.gf.limit.bean.InitLimitBean;
import com.agree.gf.limit.dao.InitLimitDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitLimitService {


    public String jssetInitLimit(AifAccountBean accountBean){
        String rs = "";
        List<InitLimitBean> list = getInitBeans(accountBean);
        for(InitLimitBean initLimitBean : list){
            rs = InitLimitDao.jsetInitLimit(initLimitBean);
            if(!rs.equals("OK")){
                System.out.println("插入下面的bean发生了错误："+initLimitBean.toString());
//                JedisWritePoolUtil.getInstance().lists().lpush("failaccounts",initLimitBean.getAccountNo());
                break;
            }
        }

        return  rs;
    }

    public String jhsetInitLimit(AifAccountBean accountBean){
        String rs = "";
        List<InitLimitBean> list = getInitBeans(accountBean);
        for(InitLimitBean initLimitBean:list){
            rs = InitLimitDao.jhsetInitLimit(initLimitBean);
            if(!rs.equals("OK")){
                System.out.println("插入下面的bean发生了错误："+initLimitBean.toString());
                break;
            }
        }
        return rs;
    }

    public void jpipeInitLimit(List<AifAccountBean> accountBeans){
        List<InitLimitBean> all = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        for(AifAccountBean account:accountBeans){
            List<InitLimitBean> list = getInitBeans(account);
            all.addAll(list);
            keys.add(account.getAccountNo());
        }
        InitLimitDao.jpipeline(all);
        for(String key:keys)
            JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",key);
        accountBeans.clear();
    }

    public String jhmsetInitLimit(AifAccountBean accountBean){
        String rs = "";
        List<InitLimitBean> list = getInitBeans(accountBean);
        rs = InitLimitDao.jhmsetMultKeyInitLimitBean(list);
        return rs;
    }

    public String jhmsetOnekeyInitLimit(AifAccountBean accountBean){
        String rs = "";
        List<InitLimitBean> list = getInitBeans(accountBean);
        rs = InitLimitDao.jhmsetOnekeyInitLimitBean(list);
        return rs;
    }


    public String jhmsetOnekeyInitLimit(List<AifAccountBean> accountBeans){
        String rs = "";
        List<InitLimitBean> all = new ArrayList<>();
        for(AifAccountBean account:accountBeans){
            List<InitLimitBean> list = getInitBeans(account);
            all.addAll(list);
        }
        rs = InitLimitDao.jhmsetAllInitBean(all);
//        accountBeans.clear();
        return rs;
    }



//    public String jhmsetInitLimit(List<AifAccountBean> beans){
//        String rs = "";
//        List<InitLimitBean> all = new ArrayList<>();
//        for(AifAccountBean bean:beans){
//            List<InitLimitBean> list = getInitBeans(bean);
//            all.addAll(list);
//            list.clear();
//            list = null;
//        }
//        rs = InitLimitDao.jhmsetMultKeyInitLimitBean(all);
//
//
//        return rs;
//    }


    private List<InitLimitBean> getInitBeans(AifAccountBean accountBean){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        List<InitLimitBean> list = new ArrayList<>();
        String serno = NumberUtil.produceNum24();
        InitLimitBean psBean = new InitLimitBean(
                accountBean.getAccountNo(),
                "personal_single",
                accountBean.getAif_normal_single(),
                "001",
                serno,
                dateFormat.format(date),
                timeFormat.format(date)
        );
//        InitLimitBean psBean = new InitLimitBean(
//                accountBean.getAccountNo(),
//                "personal_single",
//                accountBean.getAif_normal_single(),
//                "001"
//        );
        list.add(psBean);
        InitLimitBean pdBean = new InitLimitBean(
                accountBean.getAccountNo(),
                "personal_day",
                accountBean.getAif_normal_day(),
                "001",
                serno,
                dateFormat.format(date),
                timeFormat.format(date)
        );
//        InitLimitBean pdBean = new InitLimitBean(
//                accountBean.getAccountNo(),
//                "personal_day",
//                accountBean.getAif_normal_day(),
//                "001"
//        );
        list.add(pdBean);
        InitLimitBean pmBean = new InitLimitBean(
                accountBean.getAccountNo(),
                "personal_month",
                accountBean.getAif_normal_month(),
                "001",
                serno,
                dateFormat.format(date),
                timeFormat.format(date)
        );
//        InitLimitBean pmBean = new InitLimitBean(
//                accountBean.getAccountNo(),
//                "personal_month",
//                accountBean.getAif_normal_month(),
//                "001"
//        );
        list.add(pmBean);
//        InitLimitBean plsBean = new InitLimitBean(
//                accountBean.getAccountNo(),
//                "personal_single",
//                accountBean.getAif_large_single(),
//                "002",
//                serno,
//                dateFormat.format(date),
//                timeFormat.format(date)
//        );
//        list.add(plsBean);
//        InitLimitBean pldBean = new InitLimitBean(
//                accountBean.getAccountNo(),
//                "personal_day",
//                accountBean.getAif_large_day(),
//                "002",
//                serno,
//                dateFormat.format(date),
//                timeFormat.format(date)
//        );
//        list.add(pldBean);
//        InitLimitBean plmBean = new InitLimitBean(
//                accountBean.getAccountNo(),
//                "personal_month",
//                accountBean.getAif_large_month(),
//                "002",
//                serno,
//                dateFormat.format(date),
//                timeFormat.format(date)
//        );
//        list.add(plmBean);

        return list;
    }
}
