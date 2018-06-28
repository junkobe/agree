package test.com.agree.base.gf.limit.services;

//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;

public class TestLimitService {

//    @BeforeSuite
//    public void initLimit(){
//        CfgLimitDao.delCfgLimitAll();
//        CfgLimitBean organCfgLimitBean = new CfgLimitBean(
//                "organ",
//                "accountno.organ.organcode.cardtype.used",
//                "organ.organcode.cardtype.used",
//                "accountno",
//                "1");
//        String rsorgan = CfgLimitDao.jsetCfgLimitBean(organCfgLimitBean);
//        Assert.assertEquals("OK",rsorgan);
//
//        CfgLimitBean busiCfgLimitBean = new CfgLimitBean(
//                "busi",
//                "accountno.busitype.organcode.busitypecode.cardtype.checkway.used",
//                "busitype.busitypecode.checkway.used",
//                "accountno",
//                "1");
//        String rsbusi = CfgLimitDao.jsetCfgLimitBean(busiCfgLimitBean);
//        Assert.assertEquals("OK",rsbusi);
//
//
//        InitLimitDao.delInitLimitAll();
//        InitLimitBean initLimitBeanLimitBeanOrgan = new InitLimitBean(
//                "organ",
//                "organ.organcode.cardtype.used",
//                "organ.880109.01.used",
//                "99999999",
//                "12345678",
//                "20180321",
//                "120000");
//        String rsOrg = InitLimitDao.jsetInitLimitBean(initLimitBeanLimitBeanOrgan);
//        Assert.assertEquals("OK",rsOrg);
//
//        InitLimitBean initLimitBeanLimitBeanBusi = new InitLimitBean(
//                "busitype",
//                "busitype.organcod.busitypecode.checkway.used",
//                "busitype.880109.010101.token.used",
//                "10",
//                "12345678",
//                "20180321",
//                "120000");
//        String rs = InitLimitDao.jsetInitLimitBean(initLimitBeanLimitBeanBusi);
//        Assert.assertEquals("OK",rs);
//
//    }

//    @Test
//    public void testSetLimitInit(){
//        InitLimitDao.delInitLimitAll();
//        LimitService limitService = new LimitService();
//        String[] rs =  limitService.setLimitInit(
//                "organ.880109.01.used",//cfginit
//                "1000").valueof();
//        String errercode = rs[0];
//        Assert.assertEquals("000000",errercode);
//    }
//
//    @Test
//    public void testGetLimitInit(){
//        String initItem = "organ.880109.02.used";
//
//        InitLimitBean organInitLimitBean = new InitLimitBean("organ","organ.organcode.cardtype.used",initItem,"1000","12345678","20180321","120000");
//        String rsorgan = InitLimitDao.jsetInitLimitBean(organInitLimitBean);
//        Assert.assertEquals("OK",rsorgan);
//
//
//        LimitService limitService = new LimitService();
//        InitLimitBean initLimitBean = limitService.getLimitInitByItem(initItem);
//        Assert.assertEquals("organ",initLimitBean.getTypeId());
//        Assert.assertEquals("organ.organcode.cardtype.used",initLimitBean.getCfginit());
//        Assert.assertEquals(initItem,initLimitBean.getInititem());
//        Assert.assertEquals("1000",initLimitBean.getInitval());
//        Assert.assertEquals("12345678",initLimitBean.getBusi_serno() );
//        Assert.assertEquals("20180321",initLimitBean.getUpdatedate() );
//
//    }


//    @Test
//    public void testConsumeLimit(){
//
//
//
////        LimitService limitService = new LimitService();
//        String accountno = "6222000011112222";
//        String limitItemList = "organ.880109.01.used,busitype.880109.010101.token.used";
////        String limitItemList = "organ.880109.01.used";
//        String consumevalList = "1,20";
////        String delrs = RecordLimitDao.delRecordLimitAll(accountno);
//
//
//        String[] rs = LimitService.consumeLimit( accountno, limitItemList, consumevalList).valueof();
//        for(String r : rs){
//            System.out.println(r);
//        }
//    }
}
