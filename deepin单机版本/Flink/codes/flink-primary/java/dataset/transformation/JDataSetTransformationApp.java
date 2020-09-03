package flinkprimary.dataset.transformation;

import flinkprimary.DBUtils;
import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

public class JDataSetTransformationApp {


    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        mapFunction(env);
//        filterFunction(env);
//        mapPartitionFunction(env);
//        firstFunction(env);
//        flatMapFunction(env);
//        distinctFunction(env);
//        joinFunction(env);
//        outerJoinFunction(env);
        crossFunction(env);
    }

    /**
     * map算子的学习,对数据集中每个元素进行操作
     * @param env
     * @throws Exception
     */
    public static void mapFunction(ExecutionEnvironment env)throws Exception{
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        DataSource<Integer> data = env.fromCollection(list);
        data.map(new MapFunction<Integer, Integer>() {

            public Integer map(Integer input) throws Exception {
                return input + 1;
            }
        }).print();
    }

    /**
     * filter算子的学习,对集合中的数据进行过滤
     * @param env
     * @throws Exception
     */
    public static void filterFunction(ExecutionEnvironment env)throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        DataSource<Integer> data = env.fromCollection(list);
        data.map(new MapFunction<Integer, Integer>() {

            public Integer map(Integer input) throws Exception {
                return input + 1;
            }
        }).filter(new FilterFunction<Integer>() {
            public boolean filter(Integer input) throws Exception {
                return input > 5;
            }
        }).print();
    }

    /**
     * mapPartition算子的学习，分区并行处理数据
     * @param env
     * @throws Exception
     */
    public static void mapPartitionFunction(ExecutionEnvironment env)throws Exception{
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            list.add("student" + i);
        }
        DataSource<String> data = env.fromCollection(list).setParallelism(4);

        data.mapPartition(new MapPartitionFunction<String, String>() {
            public void mapPartition(Iterable<String> input, Collector<String> out) throws Exception {
                String connection = DBUtils.getConnection();
                System.out.println("connection = [" + connection + "]");
                DBUtils.returnConnection(connection);
            }
        }).print();
    }

    /**
     * 使用first算子
     * @param env
     */
    private static void firstFunction(ExecutionEnvironment env) throws Exception{
        List<Tuple2<Integer,String>> info = new ArrayList<Tuple2<Integer,String>>();
        info.add(new Tuple2<Integer, String>(1,"hadoop"));
        info.add(new Tuple2<Integer, String>(1,"spark"));
        info.add(new Tuple2<Integer, String>(1,"flink"));
        info.add(new Tuple2<Integer, String>(2,"java"));
        info.add(new Tuple2<Integer, String>(2,"scala"));
        info.add(new Tuple2<Integer, String>(2,"python"));
        info.add(new Tuple2<Integer, String>(3,"linux"));
        info.add(new Tuple2<Integer, String>(4,"windows"));

        DataSource<Tuple2<Integer,String>> data = env.fromCollection(info);
        data.groupBy(0).sortGroup(1, Order.ASCENDING).first(2).print();
        System.out.println("----分隔符-----");
        data.groupBy(0).first(2).print();

    }

    /**
     * flatMapFunction的学习，将一个字符串拆分成多个字符串
     * @param env
     * @throws Exception
     */
    private static void flatMapFunction(ExecutionEnvironment env) throws Exception{

        List<String> list = new ArrayList<String>();
        list.add("hadoop,spark");
        list.add("hadoop,flink");
        list.add("flink,flink");

        DataSource<String> data = env.fromCollection(list);
        data.flatMap(new FlatMapFunction<String, String>() {

            public void flatMap(String input, Collector<String> out) throws Exception {
                String[] splits = input.split(",");
                for(String split : splits){
                    out.collect(split);
                }
            }
        }).map(new MapFunction<String, Tuple2<String,Integer>>() {
            public Tuple2<String, Integer> map(String input) throws Exception {
                return new Tuple2<String, Integer>(input,1);
            }
        }).groupBy(0).sum(1).print();
    }

    /**
     *
     * @param env
     */
    private static void distinctFunction(ExecutionEnvironment env) throws Exception{
        List<String> list = new ArrayList<String>();
        list.add("hadoop,spark");
        list.add("hadoop,flink");
        list.add("flink,flink");

        DataSource<String> data = env.fromCollection(list);
        data.flatMap(new FlatMapFunction<String, String>() {

            public void flatMap(String input, Collector<String> out) throws Exception {
                String[] splits = input.split(",");
                for(String split : splits){
                    out.collect(split);
                }
            }
        }).distinct().print();
    }

    /**
     * 连接操作
     * @param env
     * @throws Exception
     */
    private static void joinFunction(ExecutionEnvironment env) throws Exception{

        List<Tuple2<Integer,String>> info1 = new ArrayList<Tuple2<Integer,String>>();
        info1.add(new Tuple2<Integer, String>(1,"hadoop"));
        info1.add(new Tuple2<Integer, String>(2,"java"));
        info1.add(new Tuple2<Integer, String>(3,"linux"));


        List<Tuple2<Integer,String>> info2 = new ArrayList<Tuple2<Integer,String>>();
        info2.add(new Tuple2<Integer, String>(1,"spark"));
        info2.add(new Tuple2<Integer, String>(2,"scala"));
        info2.add(new Tuple2<Integer, String>(4,"windows"));

        DataSource<Tuple2<Integer,String>> data1 = env.fromCollection(info1);
        DataSource<Tuple2<Integer,String>> data2 = env.fromCollection(info2);

        data1.join(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer,String,String>>() {

            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) throws Exception {
                return new Tuple3<Integer, String, String>(first.f0, first.f1,second.f1);
            }
        }).print();


    }


    /**
     * 外连接操作，左连接，右连接，全连接
     * @param env
     * @throws Exception
     */
    private static void outerJoinFunction(ExecutionEnvironment env) throws Exception{

        List<Tuple2<Integer,String>> info1 = new ArrayList<Tuple2<Integer,String>>();
        info1.add(new Tuple2<Integer, String>(1,"hadoop"));
        info1.add(new Tuple2<Integer, String>(2,"java"));
        info1.add(new Tuple2<Integer, String>(3,"linux"));


        List<Tuple2<Integer,String>> info2 = new ArrayList<Tuple2<Integer,String>>();
        info2.add(new Tuple2<Integer, String>(1,"spark"));
        info2.add(new Tuple2<Integer, String>(2,"scala"));
        info2.add(new Tuple2<Integer, String>(4,"windows"));

        DataSource<Tuple2<Integer,String>> data1 = env.fromCollection(info1);
        DataSource<Tuple2<Integer,String>> data2 = env.fromCollection(info2);

        data1.leftOuterJoin(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer,String>, Tuple2<Integer,String>, Tuple3<Integer,String, String>>() {

            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) throws Exception {
                if(second == null) {
                    return new Tuple3<Integer, String, String>(first.f0, first.f1, "-");

                } else {
                    return new Tuple3<Integer, String, String>(first.f0, first.f1, second.f1);
                }
            }
        }).print();

        System.out.println("------------------------------------------------------------");

        data1.rightOuterJoin(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer,String>, Tuple2<Integer,String>, Tuple3<Integer,String, String>>() {
            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) throws Exception {
                if(first == null) {
                    return new Tuple3<Integer, String, String>(second.f0, "-", second.f1);
                } else {
                    return new Tuple3<Integer, String, String>(first.f0, first.f1, second.f1);
                }
            }
        }).print();

        System.out.println("------------------------------------------------------------");

        data1.fullOuterJoin(data2).where(0).equalTo(0).with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer, String, String>>() {
            public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) throws Exception {
                if (first == null) {
                    return new Tuple3<Integer, String, String>(second.f0, "-", second.f1);
                } else if (second == null) {
                    return new Tuple3<Integer, String, String>(first.f0, first.f1, "-");
                } else {
                    return new Tuple3<Integer, String, String>(first.f0, first.f1, second.f1);
                }
            }
        }).print();

    }

    /**
     * cross算子的学习，即笛卡尔积
     * @param env
     */
    private static void crossFunction(ExecutionEnvironment env) throws Exception{

        List<String> list1 = new ArrayList<String>();
        list1.add("曼城");
        list1.add("曼联");

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(3);
        list2.add(0);
        list2.add(1);

        DataSource<String> data1 = env.fromCollection(list1);
        DataSource<Integer> data2 = env.fromCollection(list2);

        data1.cross(data2).print();

    }

}
