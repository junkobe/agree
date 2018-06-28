package com.agree.gf.limit.bean;

public class RESULTMAP {

    private String resultcode;
    private String resultmsg;

    private RESULTMAP(String code,String msg){
        this.resultcode = code;
        this.resultmsg = msg;

    }

    public String[] valueof(){
        return new String[]{resultcode,resultmsg};
    }

    //DEFAUlT-000000
    public static RESULTMAP DEFAULT_SUCC = new RESULTMAP("000000","成功");

    //DEFAUlT-99
    public static RESULTMAP DEFAULT_EXCEPTION = new RESULTMAP("999999","系统异常");

    //ARG-01
    public static RESULTMAP ARG_NULL = new RESULTMAP("0100001","入参为空");
    public static RESULTMAP ARG_ILLEGAL = new RESULTMAP("0100002","入参非法");



}
