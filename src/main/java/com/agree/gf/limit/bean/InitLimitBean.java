package com.agree.gf.limit.bean;

public class InitLimitBean {

//    /**
//     * 额度标识
//     */
//    private String typeId;
//    /**
//     * 额度初始配置项
//     */
//    private String cfginit;
//
//    /**
//     * 额度初始值项
//     */
//    private String inititem;
//    /**
//     * 额度初始值
//     */
//    private String initval;
//
//    /**
//     * 系统流水号
//     */
//    private String busi_serno;
//    /**
//     * 更新日期
//     */
//    private String updatedate;
//    /**
//     * 更新时间
//     */
//    private String updatetime;
//
//    private InitLimitBean(){
//
//    }
//
//    public InitLimitBean(String typeId, String inititem,String initval, String busi_serno, String updatedate, String updatetime) {
//        this.typeId = typeId;
//        this.inititem = inititem;
//        this.initval = initval;
//        this.busi_serno = busi_serno;
//        this.updatedate = updatedate;
//        this.updatetime = updatetime;
//    }
//
//    public InitLimitBean(String typeId, String cfginit, String inititem,String initval, String busi_serno, String updatedate, String updatetime) {
//        this.typeId = typeId;
//        this.cfginit = cfginit;
//        this.inititem = inititem;
//        this.initval = initval;
//        this.busi_serno = busi_serno;
//        this.updatedate = updatedate;
//        this.updatetime = updatetime;
//    }
//
//    public String getInitval() {
//        return initval;
//    }
//
//    public void setInitval(String initval) {
//        this.initval = initval;
//    }
//
//
//
//    public String getBusi_serno() {
//        return busi_serno;
//    }
//
//    public void setBusi_serno(String busi_serno) {
//        this.busi_serno = busi_serno;
//    }
//
//    public String getUpdatedate() {
//        return updatedate;
//    }
//
//    public void setUpdatedate(String updatedate) {
//        this.updatedate = updatedate;
//    }
//
//    public String getUpdatetime() {
//        return updatetime;
//    }
//
//    public void setUpdatetime(String updatetime) {
//        this.updatetime = updatetime;
//    }
//
//    public String getTypeId() {
//        return typeId;
//    }
//
//    public void setTypeId(String typeId) {
//        this.typeId = typeId;
//    }
//
//    public String getCfginit() {
//        return cfginit;
//    }
//
//    public void setCfginit(String cfginit) {
//        this.cfginit = cfginit;
//    }
//
//    public String getInititem() {
//        return inititem;
//    }
//
//    public void setInititem(String inititem) {
//        this.inititem = inititem;
//    }
    private String accountNo;
    private String type;
    private String initval;
    private String retype;
    private String busi_serno;
    private String updatedate;
    private String updatetime;


    private InitLimitBean(){

    }

    public InitLimitBean(String accountNo,String type,String initval,String retype){
        setAccountNo(accountNo);
        setType(type);
        setInitval(initval);
        setRetype(retype);
    }

    public InitLimitBean(String accountNo,String type,String initval,String retype,
                         String busi_serno,String updatedate,String updatetime){

        setAccountNo(accountNo);
        setType(type);
        setInitval(initval);
        setRetype(retype);
        setBusi_serno(busi_serno);
        setUpdatedate(updatedate);
        setUpdatetime(updatetime);
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getInitval() {
        return initval;
    }

    public String getRetype() {
        return retype;
    }

    public String getBusi_serno() {
        return busi_serno;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setInitval(String initval) {
        this.initval = initval;
    }

    public void setRetype(String retype) {
        this.retype = retype;
    }

    public void setBusi_serno(String busi_serno) {
        this.busi_serno = busi_serno;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "InitLimitBean{" +
                "accountNo='" + accountNo + '\'' +
                ", type='" + type + '\'' +
                ", initval='" + initval + '\'' +
                ", retype='" + retype + '\'' +
                ", busi_serno='" + busi_serno + '\'' +
                ", updatedate='" + updatedate + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
