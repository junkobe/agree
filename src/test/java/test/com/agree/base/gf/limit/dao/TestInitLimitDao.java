package test.com.agree.base.gf.limit.dao;


import com.agree.gf.limit.bean.AifAccountBean;
import com.agree.gf.limit.services.InitLimitService;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class TestInitLimitDao {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    @Test
    public void testSetInitLimitJedis(){
        System.out.println("-------testSetInitLimitJedis------");
        AifAccountBean bean = new AifAccountBean(
                "accNo0",
                "100",
                "500",
                "10000",
                "100",
                "500",
                "10000"

        );
        InitLimitService initLimitService = new InitLimitService();
//        String rs = initLimitService.jhmsetInitLimit(bean);
//        System.out.println("插入initLimit是否成功："+rs);

    }


}
