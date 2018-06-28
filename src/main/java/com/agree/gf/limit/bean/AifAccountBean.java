package com.agree.gf.limit.bean;

public class AifAccountBean {
    private String accountNo;
    private String aif_normal_single;
    private String aif_normal_day;
    private String aif_normal_month;
    private String aif_large_single;
    private String aif_large_day;
    private String aif_large_month;

    public AifAccountBean(String accountNo,String aif_normal_single,String aif_normal_day,
                          String aif_normal_month,String aif_large_single,String aif_large_day,
                          String aif_large_month){

        setAccountNo(accountNo);
        setAif_normal_single(aif_normal_single);
        setAif_normal_day(aif_normal_day);
        setAif_normal_month(aif_normal_month);
        setAif_large_single(aif_large_single);
        setAif_large_day(aif_large_day);
        setAif_large_month(aif_large_month);
    }

    public AifAccountBean(String accountNo,String aif_normal_single,String aif_normal_day,
                          String aif_normal_month){

        setAccountNo(accountNo);
        setAif_normal_single(aif_normal_single);
        setAif_normal_day(aif_normal_day);
        setAif_normal_month(aif_normal_month);
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setAif_normal_single(String aif_normal_single) {
        this.aif_normal_single = aif_normal_single;
    }

    public void setAif_normal_day(String aif_normal_day) {
        this.aif_normal_day = aif_normal_day;
    }

    public void setAif_normal_month(String aif_normal_month) {
        this.aif_normal_month = aif_normal_month;
    }

    public void setAif_large_single(String aif_large_single) {
        this.aif_large_single = aif_large_single;
    }

    public void setAif_large_day(String aif_large_day) {
        this.aif_large_day = aif_large_day;
    }

    public void setAif_large_month(String aif_large_month) {
        this.aif_large_month = aif_large_month;
    }
//

    public String getAccountNo() {
        return accountNo;
    }

    public String getAif_normal_single() {
        return aif_normal_single;
    }

    public String getAif_normal_day() {
        return aif_normal_day;
    }

    public String getAif_normal_month() {
        return aif_normal_month;
    }

    public String getAif_large_single() {
        return aif_large_single;
    }

    public String getAif_large_day() {
        return aif_large_day;
    }

    public String getAif_large_month() {
        return aif_large_month;
    }

}
