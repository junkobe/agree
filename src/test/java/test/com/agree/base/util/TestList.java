package test.com.agree.base.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestList {

    @Test
    public void testAddall(){
        List<String> all = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        l2.add("1");
        l2.add("2");
        all.addAll(l1);
        all.addAll(l2);
        System.out.println(all);
    }

}
