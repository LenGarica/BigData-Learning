package org.example.hdfsactual;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 使用HDFS API完成wordcount
 * 需求：统计结果后，将统计结果输出到HDFS上
 *
 * 功能拆解：
 *  读取HDFS上的文件  ==>使用HDFS  API
 *  业务处理，按照分隔符分割 ==>抽象出Mapper
 *  缓存处理结果  ==>Context
 *  将结果写到HDFS上  ==>HDFS API
 *
 */
public class HDFSapp2 {


    public static void main(String[] args) throws Exception{
        Properties properties = ParamsUtils.getProperties();
        Configuration configuration = new Configuration();
        //设置副本数为1
        configuration.set("dfs.replication","1");
        Path input = new Path(properties.getProperty(Constants.INPUT_PATH));
        Path output = new Path(properties.getProperty(Constants.OUTPUT_PATH));
        //获取要操作的HDFS文件系统
        FileSystem fileSystem = FileSystem.get(new URI(properties.getProperty(Constants.HDFS_URI)),configuration);
        //列出当前文件的信息和块信息
        RemoteIterator<LocatedFileStatus> remoteIterator = fileSystem.listFiles(input,false);
        MyContext context = new MyContext();

        //        MyMapper mapper = new WordCountMapper();
        //通过反射加载类，创建对象
        Class<?> clazz= Class.forName(properties.getProperty(Constants.MAPPER_CLASS));
        MyMapper mapper = (MyMapper)clazz.newInstance();



        while(remoteIterator.hasNext()){
            LocatedFileStatus file = remoteIterator.next();
            FSDataInputStream in = fileSystem.open(file.getPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";   // 用来接收读取的每行数据
            while((line = reader.readLine())!=null){
                mapper.map(line,context);
            }
            reader.close();
            in.close();
        }

        Map<Object,Object> contextMap = context.getCacheMap();

        //创建一个HDFS目录以及文件，将结果写入到此文件中
        /** new Path(a,b) Resolve a child path against a parent path. */
        FSDataOutputStream out = fileSystem.create(new Path(output , new Path(properties.getProperty(Constants.OUTPUT_FILE))));

        //使此map可迭代
        Set<Map.Entry<Object, Object>> entries = contextMap.entrySet();
        //循环取出写入
        for(Map.Entry<Object , Object> entry : entries){
            out.write((entry.getKey().toString()+"\t"+entry.getValue()+"\n").getBytes());
        }

        out.close();
        fileSystem.close();
    }


}
