package com.agree.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {

    public static Properties loadProperties(String propName){
        Properties properties = new Properties();;
        InputStream is = null;
        try{
            is = ConfigHelper.class.getClassLoader().getResourceAsStream(propName);
            properties.load(is);
        }catch (IOException e){
            e.printStackTrace();
//            logger.error("",e);
        }finally {
            if(is != null){
                try{
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
//                    logger.error("",e);
                }
            }
        }

        return properties;
    }
}
