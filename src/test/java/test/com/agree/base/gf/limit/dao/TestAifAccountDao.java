package test.com.agree.base.gf.limit.dao;

import com.agree.gf.limit.bean.AifAccountBean;
import com.agree.gf.limit.dao.AifAccountDao;
import org.junit.Test;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestAifAccountDao {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
    @Test
    public void testSetAifAccountBean(){
        int i = 0;
        while(i<10000){
            AifAccountBean bean = new AifAccountBean(
                "accNo"+i,
                "100",
                    "500",
                    "10000",
                    (100+i)+"",
                    (500+i)+"",
                    (10000+i)+""

            );
            System.out.print("插入前："+format.format(new Date())+"  ");
            String rs = AifAccountDao.jsetAifAccountBean(bean);
            System.out.println("插入后："+format.format(new Date())+"");
            Assert.assertEquals("OK",rs);
            i++;
        }
    }





}
