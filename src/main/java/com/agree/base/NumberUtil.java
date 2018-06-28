package com.agree.base;

import java.util.Random;

public class NumberUtil {
    private static long count32 = 1;
    private static long count24 = 1;
    public static String produceNum(int len){
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<len;i++){
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }


    public static String produceNum32(){
        String num = String.format("%032d",count32++);
        return  num;
    }

    public static String produceNum24(){
        String num = String.format("%024d",count24++);
        return  num;
    }

    public static void setCount32(long count){
        count32 = count;
    }

    public static void setCount24(long count){
        count24 = count;
    }

    public static void clearCount32(){
        count32 = 1;
    }

    public static void clearCount24(){
        count24 = 1;
    }


}
