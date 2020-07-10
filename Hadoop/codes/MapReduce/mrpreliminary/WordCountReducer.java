package org.example.mapreduce.mrpreliminary;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class WordCountReducer extends Reducer<Text, IntWritable , Text , IntWritable> {

    /**
     *  mapping到reducing阶段中间有一个shuffling,
     *  通过 shuffling 将相同 key 值的数据分发到同一个节点上去合并，
     *  这样才能统计出最终的结果，
     *  此时得到 K2 为每一个单词，
     *  List(V2) 为可迭代集合，
     *  V2 就是 Mapping 中的 V2；
     *
     *  例如 hello    word    hello
     *
     *  map结束输出list(hello,1)，list(word,1)，list(hello,1)
     *  shuffle结束后输出 hello，list(1,1)  word，list(1)
     *  reduce接收  hello，list(1,1)  word，list(1)进行处理
     *
     * @param key 单词
     * @param values 可迭代的list集合
     * @param context 上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //定义count用来计数
        int count = 0;

        //values是一个可以迭代的集合，将其赋值给一个迭代器
        Iterator<IntWritable> iterator = values.iterator();

        //通过迭代器取出集合中的元素
        while(iterator.hasNext()){
            IntWritable value = iterator.next();
            //使用get方法，将取出的单个元素返回成int类型
            count += value.get();
        }

        //将结果写出去
        context.write(key , new IntWritable(count));
    }
}
