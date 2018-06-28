package com.agree.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);


    public static int nullToInteger (Object str){
        int t = 0;
        if(str == null || "".equals(str))
            return t;

        t = Integer.valueOf(str.toString());
        return t;
    }


    public static String nullToString(Object t){
        return t.toString();
    }

    public static String appendStrList(String... strArgs){
        StringBuffer sb = new StringBuffer();
        for (String str : strArgs){
            sb.append(str);
        }


        return String.valueOf(sb);
    }

    public static List<String> stringSpiltToList(String str,String splitArg){
        List<String> strList = new ArrayList<String>();
        String tmpStr = str;
        int beginIdx = 0;
        int endIdx = 0;

        do {
            endIdx = tmpStr.indexOf(splitArg);
            if(endIdx < 0) {
                strList.add(tmpStr);
                break;
            }else if(endIdx == 0){
                strList.add("");
                tmpStr = tmpStr.substring(endIdx + 1, tmpStr.length());
            }
            else {
                strList.add(tmpStr.substring(beginIdx, endIdx));
                tmpStr = tmpStr.substring(endIdx + 1, tmpStr.length());
            }
        }while (endIdx >= 0 && tmpStr.length() !=0);

        return strList;
//        String nextStr = str
    }


}
