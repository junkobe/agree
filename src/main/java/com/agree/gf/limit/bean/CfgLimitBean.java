package com.agree.gf.limit.bean;

import com.agree.base.StringUtil;
import com.agree.base.redis.JedisPoolUtil;
import com.agree.gf.limit.cfg.CONFIGS;

public class CfgLimitBean {


    /**
     * 额度标识
     */
    private String typeId;
    /**
     * 额度配置项
     */
    private String cfgtype;

    /**
     * 额度初始配置项
     */
    private String cfginit;

    /**
     * 循环周期类型。1-日累计
     */
    private String retype;

    /**
     * 额度主键项(暂不启用)
     */
    private String mainkey;

    private CfgLimitBean(){

    }

    public CfgLimitBean(String _typeId,String _cfgtype,String _cfginit,String _mainkey,String _retype){
        setTypeId(_typeId);
        setCfgtype(_cfgtype);
        setCfginit(_cfginit);
        setMainkey(_mainkey);
        setRetype(_retype);
    }

    public CfgLimitBean(String _typeId,String _cfgtype,String _cfginit,String _retype){
        this(_typeId,_cfgtype,_cfginit,"",_retype);
    }

    public String getMainkey() {
        return mainkey;
    }

    public void setMainkey(String mainkey) {
        this.mainkey = mainkey;
    }

    public String getCfginit() {
        return cfginit;
    }

    public void setCfginit(String cfginit) {
        this.cfginit = cfginit;
    }

    public String getCfgtype() {
        return cfgtype;
    }

    public void setCfgtype(String cfgtype) {
        this.cfgtype = cfgtype;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getRetype() {
        return retype;
    }

    public void setRetype(String retype) {
        this.retype = retype;
    }
}
