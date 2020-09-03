package org.example.hdfsactual;

/**
 * 自定义单词处理类
 */
public class WordCountMapper implements MyMapper{

    @Override
    public void map(String line, MyContext context) {
        String[] words = line.toLowerCase().split("\t");

        //遍历数组中
        for(String word : words){
            //将单词写入到上下文中
            Object value = context.get(word);
            if(value == null){ // 表示没出现过该单词
                context.write(word,1);
            }else{
                int v = Integer.parseInt(value.toString());
                context.write(word, v+1);  // 取出单词对应的次数+1
            }
        }

    }
}
