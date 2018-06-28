package test.com.agree.base.util;

import com.agree.base.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateUtil {

    @Test
    public void TestGetDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = sdf.format(date);
        Assert.assertEquals(dateStr,DateUtil.getDateStr(date));
    }

    @Test
    public void TestGetTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String timeStr = sdf.format(date);
        Assert.assertEquals(timeStr,DateUtil.getTimeStr(date));
    }
}
