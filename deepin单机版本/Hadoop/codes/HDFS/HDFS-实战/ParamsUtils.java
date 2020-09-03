package org.example.hdfsactual;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取属性配置文件
 */
public class ParamsUtils {
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(ParamsUtils.class.getClassLoader().getResourceAsStream("hdfs.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Properties getProperties() throws Exception{
        return properties;
    }



}
