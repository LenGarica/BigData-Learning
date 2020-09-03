package flinkprimary.dataset.discache;

import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class JDistributedCacheApp {


    public static void main(String[] args) throws Exception {

        //获取环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        String filePath = "file:///home/willhope/data/input/hello.txt";

        //注册
        env.registerCachedFile(filePath,"cache-java");

        DataSource<String> data = env.fromElements("hadoop","spark","flink","storm");

        data.map(new RichMapFunction<String , String>() {

            List<String> list = new ArrayList<String>();

            @Override
            public void open(Configuration parameters) throws Exception {
                File file = getRuntimeContext().getDistributedCache().getFile("cache-java");
                List<String> lines = FileUtils.readLines(file);
                for(String s : lines){
                    list.add(s);
                    System.out.println(s);
                }
            }

            public String map(String value) throws Exception {
                return value;
            }
        }).print();



    }


}
