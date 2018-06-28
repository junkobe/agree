package test.com.agree.base.util;

import com.agree.base.NumberUtil;
import org.junit.Test;

public class TestNumberUtil {

    @Test
    public void testNumProduce(){
        System.out.println("-------testNumProduce------");
        System.out.println("生成随机数是："+NumberUtil.produceNum(32));
    }

    @Test
    public void testNum24(){
        NumberUtil.clearCount24();
        for(int i = 0;i<100;i++)
            System.out.println(NumberUtil.produceNum24());
        NumberUtil.clearCount24();
        for(int i = 0;i<100;i++)
            System.out.println(NumberUtil.produceNum24());
        NumberUtil.clearCount24();
    }
}
