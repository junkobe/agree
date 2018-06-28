package com.agree.gf.limit.bean;

import com.agree.base.DateUtil;

import java.util.Date;

public class RecordLimitBean {

    private String mainkey;
    private String limitItem;

    private String recordLimit;

    private String chnlDate;
    private String chnlTime;
    private String chnlSerno;
    private String chnlId;

    private String busiDate;

    private String sysDate;
    private String sysTime;
    private String sysSerno;

    private String latestTrxcode;
    private String latestAmount;

    private RecordLimitBean(){

    }
    public RecordLimitBean(String mainkey,String limitItem,String recordLimit, String chnlDate, String chnlTime, String chnlSerno, String chnlId, String busiDate, String sysDate, String sysTime, String sysSerno, String latestTrxcode, String latestAmount) {
        this.mainkey = mainkey;
        this.limitItem = limitItem;

        this.recordLimit = recordLimit;
        this.chnlDate = chnlDate;
        this.chnlTime = chnlTime;
        this.chnlSerno = chnlSerno;
        this.chnlId = chnlId;
        this.busiDate = busiDate;
        this.sysDate = sysDate;
        this.sysTime = sysTime;
        this.sysSerno = sysSerno;
        this.latestTrxcode = latestTrxcode;
        this.latestAmount = latestAmount;
    }

    public RecordLimitBean(String mainkey,String limitItem,String recordLimit, String latestTrxcode, String latestAmount) {
        this( mainkey,
                limitItem,
                recordLimit,
                "",
                "",
                "",
                "",
                "",
                DateUtil.getDateStr(new Date()),
                DateUtil.getTimeStr(new Date()),
                "",
                latestTrxcode,
                latestAmount);

    }



    public String getRecordLimit() {
        return recordLimit;
    }

    public void setRecordLimit(String recordLimit) {
        this.recordLimit = recordLimit;
    }

    public String getChnlDate() {
        return chnlDate;
    }

    public void setChnlDate(String chnlDate) {
        this.chnlDate = chnlDate;
    }

    public String getChnlTime() {
        return chnlTime;
    }

    public void setChnlTime(String chnlTime) {
        this.chnlTime = chnlTime;
    }

    public String getChnlSerno() {
        return chnlSerno;
    }

    public void setChnlSerno(String chnlSerno) {
        this.chnlSerno = chnlSerno;
    }

    public String getChnlId() {
        return chnlId;
    }

    public void setChnlId(String chnlId) {
        this.chnlId = chnlId;
    }

    public String getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }

    public String getSysSerno() {
        return sysSerno;
    }

    public void setSysSerno(String sysSerno) {
        this.sysSerno = sysSerno;
    }

    public String getLatestTrxcode() {
        return latestTrxcode;
    }

    public void setLatestTrxcode(String latestTrxcode) {
        this.latestTrxcode = latestTrxcode;
    }

    public String getLatestAmount() {
        return latestAmount;
    }

    public void setLatestAmount(String latestAmount) {
        this.latestAmount = latestAmount;
    }


    public String getMainkey() {
        return mainkey;
    }

    public void setMainkey(String mainkey) {
        this.mainkey = mainkey;
    }

    public String getLimitItem() {
        return limitItem;
    }

    public void setLimitItem(String limitItem) {
        this.limitItem = limitItem;
    }
}
