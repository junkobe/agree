package test.com.agree.base.util;

import com.agree.base.StringUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStringUtil {

    @Test
    public void TestnullToInteger1(){
        assertEquals (1,StringUtil.nullToInteger("1"));
    }

    @Test
    public void TestnullToInteger2(){
        assertEquals (1,StringUtil.nullToInteger(1));
    }

    @Test
    public void TestnullToInteger3(){
        assertEquals (0,StringUtil.nullToInteger(null));
    }

    @Test
    public void TestnullToInteger4(){
        assertEquals (1,StringUtil.nullToInteger(1.0));
    }

    @Test
    public void TestStringSpiltToList(){
        assertEquals(4,StringUtil.stringSpiltToList("111.22.333.444",".").size());
    }

    @Test
    public void TestStringSpiltToList2(){
        assertEquals(5,StringUtil.stringSpiltToList("111.22..333.444",".").size());
    }

}
