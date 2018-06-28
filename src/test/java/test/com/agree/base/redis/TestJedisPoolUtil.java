package test.com.agree.base.redis;

import com.agree.base.redis.JedisPoolUtil;
import com.agree.base.redis.JedisReadPoolUtil;
import com.agree.base.redis.JedisWritePoolUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ScanResult;

import java.util.*;


public class TestJedisPoolUtil {

    private static final Logger logger = LoggerFactory.getLogger(TestJedisPoolUtil.class);


    @Test
    public void testScanJedis(){
        System.out.println("-----testScanJedis-----");
//        JedisPoolUtil poolUtil = JedisReadPoolUtil.getInstance();
        String cursor = "0";
        int cur = 0;
        do{
            ScanResult<String> result = JedisReadPoolUtil.getInstance().scan().scan(cursor,"A_*",20);
            List<String> keys = result.getResult();
            for (String key: keys) {
                System.out.println("key"+key);
            }
            cursor = result.getStringCursor();
            System.out.println("cursor:"+cursor);
            cur = Integer.parseInt(cursor);
        }while(cur>0);
    }

    @Test
    public void testGetAndSetAccountJedis(){
        System.out.println("------testGetAndSetAccountJedis------");
//        JedisPoolUtil readPool = JedisReadPoolUtil.getInstance();
        ScanResult<String> result = JedisReadPoolUtil.getInstance().scan().scan("0","A_*",20);
        List<String> keys = result.getResult();



    }

    @Test
    public void testGetJedis() {
//        System.out.println("-------testGetJedis-----");
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
////        jedisPoolUtil.strings().set("h1", "test");
//        String rs = jedisPoolUtil.strings().set("h1", "test");
////        System.out.println(rs);
//        Assert.assertEquals("OK", rs);
    }

    @Test
    public void testSetAndDelJedis() {
//        System.out.println("-------testSetAndDelJedis");
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        jedisPoolUtil.strings().set("h1", "test");
//        Assert.assertEquals(true,jedisPoolUtil.keys().exists("h1"));
//        jedisPoolUtil.keys().del("h1");
//        Assert.assertEquals(false,jedisPoolUtil.keys().exists("h1"));
    }

    @Test
    public void testSetAndGetJedis() {
//        System.out.println("-------testSetAndGetJedis");
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        jedisPoolUtil.keys().del("h1");
//        String rs1 = jedisPoolUtil.strings().set("h1", "test");
//        Assert.assertEquals("OK", rs1);
//        String rs2 = jedisPoolUtil.strings().get("h1");
//        Assert.assertEquals(rs2, "test");
    }

    @Test
    public void testHmget(){
//        System.out.println("-------testHmget");
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        Map<String,String> testMap = new HashMap<String,String>();
//        testMap.put("f1","1");
//        testMap.put("f2","2");
//        testMap.put("f3","3");
//        jedisPoolUtil.hash().hmset("testHm",testMap);
//        List<String> rs = jedisPoolUtil.hash().hmget("testHm","f1","f2","f3");
//        for(String s : rs){
//            System.out.println(s);
//        }
    }

    @Test
    public void testLuaScriptReturn(){
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        String script = "return 1";
//        List<String> keys  = new ArrayList<>();
//        List<String> args  = new ArrayList<>();
//        Object results = jedisPoolUtil.luas().luaeval(script,keys,args);
//        if(results!=null )
//            System.out.println(results);
////        if(results != null && results.size() > 0)
////            for(String rs : results){
////                System.out.println(rs);
////            }
    }

    @Test
    public void testLuaScriptCallMSet(){
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        String script = "local result={} " +
//                " for i,v in ipairs(KEYS) do " +
//                " result[i] = redis.call('set',v,ARGV[i]) "+
//                " end "+
//                " return result ";
//        List<String> keys  = new ArrayList<>();
//        List<String> args  = new ArrayList<>();
//
//        keys.add("test01");
//        keys.add("test02");
//        keys.add("test03");
//
//        args.add("1");
//        args.add("2");
//        args.add("3");
//
//        Object results = jedisPoolUtil.luas().luaeval(script,keys,args);
//        if(results != null )
////            for(String rs : results){
//                System.out.println(results);
////            }
    }

    @Test
    public void testLuaScriptCallMGet(){
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        String script = "local result={} " +
//                " for i,v in ipairs(KEYS) do " +
//                " result[i] = redis.call('get',v) "+
//                " end "+
//                " return result ";
//        List<String> keys  = new ArrayList<>();
//        List<String> args  = new ArrayList<>();
//
//        keys.add("test01");
//        keys.add("test02");
//        keys.add("test03");
//
//        Object results = jedisPoolUtil.luas().luaeval(script,keys,args);
//        if(results != null )//&& results.size() > 0)
////            for(String rs : results){
//                System.out.println(results);
////            }
    }

    @Test
    public void testLuaScriptCallHMSet(){
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        jedisPoolUtil.keys().del("test01");
//        jedisPoolUtil.keys().del("test02");
//        jedisPoolUtil.keys().del("test03");
//        //lua的数组是从1开始
//        String script = "local result={} " +
//                "local j = 1 " +
//                "local step = 2 " +
//                " for i,k in ipairs(KEYS) do " +
////                " result[i] = k ..'['..i..','..j..']:'.. ARGV[j]..','..ARGV[j+1]..'-' "+
//                " result[i] = redis.call('hmset',k,ARGV[j],ARGV[j+1]) "+
//                " j = j + 2 " +
//                " end " +
//                " return result ";
//        List<String> keys  = new ArrayList<>();
//        List<String> args  = new ArrayList<>();
//
//        keys.add("test01");
//        keys.add("test02");
//        keys.add("test03");
//
//        args.add("field01");
//        args.add("01");
//        args.add("field02");
//        args.add("02");
//        args.add("field03");
//        args.add("03");
//
//        Object results = jedisPoolUtil.luas().luaeval(script,keys,args);
////        if(results != null && results.size() > 0)
////            for(Object rs : results){
//                System.out.println(results);
////            }
    }

    @Test
    public void testLuaScriptCallHMGet(){
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        String script = "local result={} " +
//                " for i,k in ipairs(KEYS) do " +
//                " if (i*2)%2 == 0 " +
//                " then " +
//                " result[i] = redis.call('hmget',k,ARGV[i*2],ARGV[i*2+1]) "+
//                " end " +
//                " end " +
//                " return result ";
//        //lua的数组是从1开始
//        script = "local result={} " +
//                " for i,k in ipairs(KEYS) do " +
////                " result[i] = k ..'['..i..','..j..']:'.. ARGV[j]..','..ARGV[j+1]..'-' "+
//                " result[i] = redis.call('hmget',k,ARGV[i]) "+
//                " end " +
//                " return result ";
//        List<String> keys  = new ArrayList<>();
//        List<String> args  = new ArrayList<>();
//
//        keys.add("test01");
//        keys.add("test02");
//        keys.add("test03");
//
//        args.add("field01");
//        args.add("field02");
//        args.add("field03");
//
//        Object results = jedisPoolUtil.luas().luaeval(script,keys,args);
////        if(results != null && results.size() > 0)
////            for(Object rs : results){
//                System.out.println(results);
////            }
    }



    @Test
    public void testReadAndWriteJedis(){
        System.out.println("-----testReadAndWriteJedis-----");
//        JedisPoolUtil readPool = JedisReadPoolUtil.getInstance();
//        JedisPoolUtil writePool = JedisWritePoolUtil.getInstance();
        String rs1 = JedisReadPoolUtil.getInstance().strings().get("h1");
        Assert.assertEquals("test",rs1);
        String rs2 = JedisWritePoolUtil.getInstance().strings().set("h1",rs1);
        Assert.assertEquals("OK",rs2);
    }

    @Test
    public void testReverseReadAndWriteJedis(){
        System.out.println("-----testReverseReadAndWriteJedis----");
//        JedisPoolUtil readPool = JedisReadPoolUtil.getInstance();
//        JedisPoolUtil writePool = JedisWritePoolUtil.getInstance();
        Set<String> keys = JedisReadPoolUtil.getInstance().keys().keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String key = it.next();
            String value = JedisReadPoolUtil.getInstance().strings().get(key);
            JedisWritePoolUtil.getInstance().strings().set(value,key);
        }
    }

//    @Test
//    public void testTransaction01(){
//        System.out.println("-------testTransaction01");
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        Transaction tx = jedisPoolUtil.getTranc();
//        //test set and get
//        tx.set("txc01","txc01");
//        String val = tx.get("txc01").get();
//        List<Object> l = tx.exec();
//        System.out.println(l);
//        System.out.println(val);
//        Assert.assertEquals("txc01",val);
//
//    }
//
//    @Test
//    public void testTransaction02(){
//        System.out.println("-------testTransaction01");
//        String testStr = "txc01";
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        jedisPoolUtil.strings().set(testStr,testStr);
//        Transaction tx = jedisPoolUtil.getTranc();
//
//        //test set and get
//        Response<String> rspGet = tx.get("txc01");
//        Response<String> rspSet = tx.set("txc01","txc02");
//        System.out.println(rspSet);
//
//        System.out.println(rspGet);
//
//        List<Object> l = tx.exec();
//        System.out.println(rspGet.get());
//        System.out.println(rspSet.get());
//        System.out.println("list:"+l);
////        System.out.println(val);
////        Assert.assertEquals("txc01",val);
//
//    }
//
//    @Test
//    public void testTransaction03(){
//        System.out.println("-------testTransaction01");
//        String testStr = "txc01";
//        JedisPoolUtil jedisPoolUtil = JedisPoolUtil.getInstance();
//        jedisPoolUtil.strings().set(testStr,testStr);
//        Transaction tx = jedisPoolUtil.getTranc();
//
//        //test set and get
//        Response<String> rspGet = tx.get("txc01");
//        Response<String> rspSet = tx.set("txc01","txc02");
//        System.out.println(rspSet);
//
//        System.out.println(rspGet);
//
//        String l = tx.discard();
////        System.out.println(rspGet.get());
////        System.out.println(rspSet.get());
//        System.out.println("list:"+l);
////        System.out.println(val);
////        Assert.assertEquals("txc01",val);
//
//    }


}
