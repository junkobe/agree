package com.agree.gf.limit.services;

import com.agree.base.redis.JedisPoolUtil;
import com.agree.base.redis.JedisReadPoolUtil;
import com.agree.base.redis.JedisWritePoolUtil;
import com.agree.gf.limit.bean.AifAccountBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ScanResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MigrateService {
    private ExecutorService threadPool = Executors.newFixedThreadPool(30);
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    public void migrate(long total,String startCursor,int type){
//        JedisReadPoolUtil readPool = JedisReadPoolUtil.getInstance();
//        JedisWritePoolUtil.getInstance();
        String cursor = startCursor;
        int cur = 0;
        long count = 0;
        Date start = new Date();
        System.out.println("迁移开始时间："+format.format(start));
        do{
//            Date oneStart = new Date();
//            Date scanStart = new Date();
            ScanResult<String> result = JedisReadPoolUtil.getInstance().scan().scan(cursor,"A_*",150);
//            Date scanEnd = new Date();
            List<String> keys = result.getResult();
//            System.out.println("扫描时间："+format.format(scanStart)+"-"+format.format(scanEnd)+String.format(",获取%d个",keys.size()));
//            System.out.println(format.format(new Date())+String.format("  取得%d个key",keys.size()));


            if((count+keys.size())>total){
                int endIndex = Integer.parseInt(String.valueOf(total-count));
                keys = keys.subList(0,endIndex);
            }

            if (type==0){
//                System.out.println("插入方式：hmset");
                hmsetConverToRedis(keys);
            }else if(type==1){
                //                System.out.println("插入方式：hmset onekey");
                hmsetOneKeyConverToRedis1(keys);
//                hmsetOneKeyConverToRedis(keys);

            }else if(type==2){
                //                System.out.println("插入方式：string");
                ssetConverToRedis(keys);
            }

            for(int i = 0;i<keys.size();i++){
                count++;
                if(count%10000==0){
                    Date now = new Date();
                    long time = now.getTime()-start.getTime();
                    time = time/1000;
                    long avg = (count*3)/time;
                    System.out.println(format.format(now)+"  "+String.format("已迁移%d个账户信息,平均插入%d（条/秒）",count,avg));
                }
            }
//            count = count+keys.size();
            cursor = result.getStringCursor();
            cur = Integer.parseInt(cursor);
//            Date oneEnd = new Date();
//            System.out.println("处理一批key时间："+format.format(oneStart)+"-"+format.format(oneEnd));

        }while(cur>0&&count<total);


        threadPool.shutdown();
        while(!threadPool.isTerminated()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("迁移结束时间："+format.format(new Date()));

    }


    private void ssetConverToRedis(List<String> keys){

        InitLimitService initLimitService = new InitLimitService();
        List<Future> tasks = new ArrayList<>();
        for(String key : keys){
            tasks.add(threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String accountNo = key.substring(2);
                    Map<String,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
                    AifAccountBean bean = new AifAccountBean(
                            accountNo,
                            map.get("aif_normal_single"),
                            map.get("aif_normal_day"),
                            map.get("aif_normal_month")
        //                    map.get("aif_large_single"),
        //                    map.get("aif_large_day"),
        //                    map.get("aif_large_month")
                    );
//                    Date hmsetStart = new Date();
                    String rs = initLimitService.jssetInitLimit(bean);
//                    Date hmsetEnd = new Date();
//                    System.out.println("hmset时间"+format.format(hmsetStart)+"-"+format.format(hmsetEnd));
                    if(rs.equals("OK")){
                        JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",accountNo);

                    }else{
                        JedisReadPoolUtil.getInstance().lists().lpush("failaccounts",accountNo);
                    }
                }
            }));
        }
//        threadPool.wait();

        boolean flag = false;
        while(!flag){
            for(Future task:tasks){
                if(task.isDone()){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
        }
        tasks.clear();

    }

    private void hsetConverToRedis(List<String> keys){
        InitLimitService initLimitService = new InitLimitService();
        for(String key : keys){
            String accountNo = key.substring(2);
            Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
            AifAccountBean bean = new AifAccountBean(
                    accountNo,
                    map.get("aif_normal_single"),
                    map.get("aif_normal_day"),
                    map.get("aif_normal_month")
//                    map.get("aif_large_single"),
//                    map.get("aif_large_day"),
//                    map.get("aif_large_month")
            );
            initLimitService.jhsetInitLimit(bean);
        }
    }

    private void pipelineConverToRedis(List<String> keys){
//        int count = 0;
        List<AifAccountBean> accountlist = new ArrayList<>();
//        List<Future> tasks = new ArrayList<>();
        InitLimitService service = new InitLimitService();
        for(String key:keys){
            String accountNo = key.substring(2);
            Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
            AifAccountBean bean = new AifAccountBean(
                    accountNo,
                    map.get("aif_normal_single"),
                    map.get("aif_normal_day"),
                    map.get("aif_normal_month")
            );

            accountlist.add(bean);

        }
        service.jpipeInitLimit(accountlist);
//        for(String key:keys){
//            String accountNo = key.substring(2);
//        }
    }

    private void pipelineThreadConverToRedis(List<String> keys){
        int count = 0;
        List<AifAccountBean> accountlist = new ArrayList<>();
        List<Future> tasks = new ArrayList<>();
        InitLimitService service = new InitLimitService();
        for(String key:keys){
            String accountNo = key.substring(2);
            Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
            AifAccountBean bean = new AifAccountBean(
                    accountNo,
                    map.get("aif_normal_single"),
                    map.get("aif_normal_day"),
                    map.get("aif_normal_month")
            );
            accountlist.add(bean);
            count++;
            if(count==10){
                final List<AifAccountBean> list = accountlist;
                tasks.add(
                    threadPool.submit(new Runnable() {
                        @Override
                        public void run() {

                            service.jpipeInitLimit(list);
                            for(AifAccountBean bean:list){
                                String accountNo = bean.getAccountNo();
                                JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",accountNo);
                            }
//                            list.clear();
                        }
                }));
                count=0;
                accountlist = new ArrayList<>();
            }
        }

        if(count!=0&&count<10){
            service.jpipeInitLimit(accountlist);
            for(AifAccountBean bean:accountlist){
                String accountNo = bean.getAccountNo();
                JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",accountNo);
            }
        }

        boolean flag = false;
        while(!flag){
            for(Future task:tasks){
                if(task.isDone()){
                    flag = true;
                }else{
                    break;
                }
            }
        }
        tasks.clear();
//        tasks = null;

    }

    private void hmsetConverToRedis(List<String> keys){
        InitLimitService initLimitService = new InitLimitService();
        AccountService accountService = new AccountService();
        List<Future> tasks = new ArrayList<>();
        for(String key : keys){
            tasks.add(threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String accountNo = key.substring(2);
//                    boolean isExist = accountService.exist("successaccounts",accountNo);
//                    if(isExist){
//                        return;
//                    }
                    Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
                    AifAccountBean bean = new AifAccountBean(
                            accountNo,
                            map.get("aif_normal_single"),
                            map.get("aif_normal_day"),
                            map.get("aif_normal_month")
        //                    map.get("aif_large_single"),
        //                    map.get("aif_large_day"),
        //                    map.get("aif_large_month")
                            );
                    String rs = initLimitService.jhmsetInitLimit(bean);
                    if(rs.equals("OK")){
//                        JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",accountNo);
                        JedisWritePoolUtil.getInstance().sets().sadd("successaccounts",accountNo);
                    }else {
//                        JedisWritePoolUtil.getInstance().lists().lpush("failaccounts",accountNo);
                        JedisWritePoolUtil.getInstance().sets().sadd("failaccounts",accountNo);
                    }


                }
            }));
        }

        boolean flag = false;
        while(!flag){
            for(Future task:tasks){
                if(task.isDone()){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
        }
        tasks.clear();
//        tasks = null;


    }

    private void hmsetOneKeyConverToRedis1(List<String> keys){
        InitLimitService initLimitService = new InitLimitService();
//        AccountService accountService = new AccountService();
        List<Future> tasks = new ArrayList<>();
        List<String> partKeys = new ArrayList<>();
        int count = 0;
        for (String key : keys){

            tasks.add(threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String accountNo = key.substring(2);
//                    boolean isExist = accountService.exist("successaccounts",accountNo);
//                    if(isExist){
//                        return;
//                    }
                    Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
                    AifAccountBean bean = new AifAccountBean(
                            accountNo,
                            map.get("aif_normal_single"),
                            map.get("aif_normal_day"),
                            map.get("aif_normal_month"));
                    String rs = initLimitService.jhmsetOnekeyInitLimit(bean);
                    if(rs.equals("OK")){
//                        JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",accountNo);
                        JedisWritePoolUtil.getInstance().sets().sadd("successaccounts",accountNo);
                    }else{
//                        JedisWritePoolUtil.getInstance().lists().lpush("failaccounts",accountNo);
                        JedisWritePoolUtil.getInstance().sets().sadd("failaccounts",accountNo);
                    }
                }
            }));

        }
        boolean flag = false;
        while(!flag){
            for(Future task:tasks){
                if(task.isDone()){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
        }
        tasks.clear();

    }

    private void hmsetOneKeyConverToRedis(List<String> keys){
        InitLimitService initLimitService = new InitLimitService();
        List<Future> tasks = new ArrayList<>();
        int count = 0;
        List<AifAccountBean> accounts = new ArrayList<>();
        Date start = new Date();
        for(String key : keys){
//            String accountNo = key.substring(2);
//            Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
//            AifAccountBean bean = new AifAccountBean(
//                    accountNo,
//                    map.get("aif_normal_single"),
//                    map.get("aif_normal_day"),
//                    map.get("aif_normal_month"));
//            tasks.add(threadPool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    String rs = initLimitService.jhmsetOnekeyInitLimit(bean);
////                    if(rs.equals("OK")){
////                        JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",bean.getAccountNo());
////
////                    }else{
////                        JedisWritePoolUtil.getInstance().lists().lpush("failaccounts",bean.getAccountNo());
////                    }
//                }
//            }));
            Date javaStart = new Date();
            String accountNo = key.substring(2);
//            Date hgetallStart = new Date();
            Map<String ,String> map = JedisReadPoolUtil.getInstance().hash().hgetAll(key);
//            Date hgetallEnd = new Date();
//            System.out.println("hgetall的时间："+format.format(hgetallStart)+"-"+format.format(hgetallEnd));
            AifAccountBean bean = new AifAccountBean(
                    accountNo,
                    map.get("aif_normal_single"),
                    map.get("aif_normal_day"),
                    map.get("aif_normal_month"));
            accounts.add(bean);
            Date javaEnd = new Date();
            System.out.println("数据转换时间："+format.format(javaStart)+"-"+format.format(javaEnd));
            count++;
            if(count%100==0){
                final List<AifAccountBean> _accounts = accounts;
                Date threadStart = new Date();
                tasks.add(
                        threadPool.submit(new Runnable() {
                            @Override
                            public void run() {
                                Date tStart = new Date();
                                String rs = initLimitService.jhmsetOnekeyInitLimit(_accounts);
                                Date hmsetEnd = new Date();
                                System.out.println("hmset时间"+format.format(tStart)+"-"+format.format(hmsetEnd)+String.format("，插入%d条数据",_accounts.size()*3));
//                                if(rs.equals("OK")){
//                                    Date rsStart = new Date();
//                                    for(AifAccountBean bean:_accounts){
//                                        JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",bean.getAccountNo());
//                                    }
//                                    Date rsEnd = new Date();
//                                    System.out.println("list的插入："+format.format(rsStart)+"-"+format.format(rsEnd));
//                                }else{
//                                    for(AifAccountBean bean:_accounts){
//                                        JedisWritePoolUtil.getInstance().lists().lpush("failaccounts",bean.getAccountNo());
//                                    }
//                                }
                                _accounts.clear();
                                Date tEnd = new Date();
                                System.out.println("执行完一个线程的时间："+format.format(tStart)+"-"+format.format(tEnd));
                            }
                        })
                );
                Date threadEnd = new Date();
                System.out.println("提交一个线程时间："+format.format(threadStart)+"-"+format.format(threadEnd));
                count = 0;
                accounts = new ArrayList<>();
            }
        }
        Date forEnd = new Date();
        System.out.println("for时间："+format.format(start)+"-"+format.format(forEnd));

        if(count>0&&accounts.size()>0){
            String rs = initLimitService.jhmsetOnekeyInitLimit(accounts);
            if(rs.equals("OK")){
                for(AifAccountBean bean:accounts){
                    JedisWritePoolUtil.getInstance().lists().lpush("successaccounts",bean.getAccountNo());
                }
            }else{
                for(AifAccountBean bean:accounts){
                    JedisWritePoolUtil.getInstance().lists().lpush("failaccounts",bean.getAccountNo());
                }
            }
        }

        boolean flag = false;
        while(!flag){
            for(Future task:tasks){
                if(task.isDone()){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
        }
        tasks.clear();
        Date end = new Date();
        System.out.println("调用一次hmsetOneKeyConverToRedis方法时间："+format.format(start)+"-"+format.format(end));
    }


//    public static void main(String args[]){
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        System.out.println(list);
//
//        list.clear();
//        System.out.println(list.size());
//        list.add("c");
//        list.add("d");
//        System.out.println(list);
//    }

}
