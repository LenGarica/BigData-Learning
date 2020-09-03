package org.example.hdfsactual;

public interface MyMapper {

    /**
     *
     * @param line 读取到的每行数据
     * @param context 上下文/缓存
     */
    void map(String line , MyContext context);


}
