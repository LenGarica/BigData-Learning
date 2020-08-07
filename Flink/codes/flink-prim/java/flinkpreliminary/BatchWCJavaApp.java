package com.zyx.flink.java;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Flink词频统计
 */
public class BatchWCJavaApp {

    public static void main(String[] args) throws Exception{

        String input = "file:///home/willhope/data/input";

        //获取执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //读取数据
        DataSet<String> text = env.readTextFile(input);

        //处理，使用Lambda函数式编程
        text.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (values, collector) -> {
            String[] tokens = values.toLowerCase().split("\t");
            for(String token : tokens){
                if(token.length() > 0){
                    collector.collect(new Tuple2<String,Integer>(token,1));
                }
            }
        }).groupBy(0).sum(1).print();

        //非Lambda式
//        text.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
//            @Override
//            public void flatMap(String values, Collector<Tuple2<String, Integer>> collector) throws Exception {
//                String[] tokens = values.toLowerCase().split("\t");
//                for(String token : tokens){
//                    if(token.length() > 0){
//                        collector.collect(new Tuple2<String,Integer>(token,1));
//                    }
//                }
//            }
//        }).groupBy(0).sum(1).print();

    }

}
