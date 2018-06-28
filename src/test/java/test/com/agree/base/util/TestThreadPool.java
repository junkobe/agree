package test.com.agree.base.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadPool {

    @Test
    public void testThreads(){
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        List<Future> tasks = new ArrayList<>();
        for(int i = 0;i<20;i++){
           tasks.add(threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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
                    break;
                }
            }
        }

        tasks.clear();
        for(int i = 0;i<20;i++){
            tasks.add(threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        flag = false;
        while(!flag){
            for(Future task:tasks){
                if(task.isDone()){
                    flag = true;
                }else{
                    break;
                }
            }
        }

    }
}
