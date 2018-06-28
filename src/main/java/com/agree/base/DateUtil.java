package com.agree.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

//    private Date date;
    private static SimpleDateFormat sdf;
    public static String dateFormat = "YYYYMMddHHmmss";

    private static void initDate(){
        if(sdf == null)
            sdf = new SimpleDateFormat(dateFormat);
//        if(date == null)
//            date = new Date();
    }

    public static String getDateStr(Date date){
        initDate();
        return  sdf.format(date).substring(0,8);
    }

    public static String getTimeStr(Date date){
        initDate();
        return  sdf.format(date).substring(8);
    }
}
