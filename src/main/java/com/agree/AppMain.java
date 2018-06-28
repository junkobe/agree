package com.agree;

import com.agree.base.NumberUtil;
import com.agree.base.redis.JedisReadPoolUtil;
import com.agree.gf.limit.bean.AifAccountBean;
import com.agree.gf.limit.dao.AifAccountDao;
import com.agree.gf.limit.services.MigrateService;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppMain {

    /**
     * args[0] itotal：插入总数
     * args[1] mtotal：迁移总数
     * args[2] cursor：游标，0开始
     * args[4] 迁移后数据存储结构：hash:0,hash_onekey:1,string:2
     * @param args
     */
    public static void main(String args[]){
        ExecutorService threadPool = Executors.newFixedThreadPool(30);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        int i = 0;
        long itotal = 0;
        long mtotal = 0;
        if(args==null||args.length<2){
            System.out.println("请以数据总数（万为单位）和游标cursor为参数");
            return;
        }

        itotal = Long.parseLong(args[0]);
        itotal = 10000*itotal;
        mtotal = Long.parseLong(args[1]);
        mtotal = 10000*mtotal;
        String cursor = args[2];
        int mtype = Integer.parseInt(args[3]);


        if(itotal>0){
            System.out.println("原始数据插入前："+format.format(new Date())+"  ");

            List<Future> tasks = new ArrayList<>();
            NumberUtil.clearCount32();
            long count = JedisReadPoolUtil.getInstance().size().dbSize();
            NumberUtil.setCount32(count);
            while(i<itotal){
                AifAccountBean bean = new AifAccountBean(
                        NumberUtil.produceNum32(),
                        "100",
                        "500",
                        "10000",
                        "1000",
                        "5000",
                        "100000"
                );
                tasks.add(threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        String rs = AifAccountDao.jsetAifAccountBean(bean);
//                        if(rs.equals(""))
                        Assert.assertEquals("OK",rs);
                    }
                }));
                i++;
                if(i%30==0){
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
                }
//                if(i%100000==0){
//
//                }
            }
            NumberUtil.clearCount32();
            System.out.println("原始插入后："+format.format(new Date())+"\n\n");
        }

        new MigrateService().migrate(mtotal,cursor,mtype);

    }
}
