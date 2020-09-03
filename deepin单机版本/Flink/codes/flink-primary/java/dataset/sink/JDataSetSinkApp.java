package flinkprimary.dataset.sink;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.core.fs.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class JDataSetSinkApp {

    public static void main(String[] args) throws Exception{


        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        DataSource<Integer> data = env.fromCollection(list);
        String filePath = "file:///home/willhope/data/sink-out-java";
        data.writeAsText(filePath, FileSystem.WriteMode.OVERWRITE).setParallelism(2);


        env.execute("JDataSetSinkApp");
    }

}
