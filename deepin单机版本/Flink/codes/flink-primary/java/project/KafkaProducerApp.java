package project;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class KafkaProducerApp {

    public static void main(String[] args) throws Exception{

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "willhope-pc:9092");
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", StringSerializer.class.getName());

        //key为string类型，value为string类型
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);

        String topic = "test";

        //通过死循环一直往kafka中生产数据
        while (true){
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("imooc").append("\t")
                    .append("CN").append("\t")
                    .append(getLevels()).append("\t")
                    .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\t")
                    .append(getIps()).append("\t")
                    .append(getDomains()).append("\t")
                    .append(getTraffic()).append("\t");

            System.out.println(stringBuilder.toString());

            producer.send(new ProducerRecord<String, String>(topic,stringBuilder.toString()));

            Thread.sleep(2000);

        }
    }

    //生产流量数据
    private static long getTraffic() {
        return new Random().nextInt(10000);
    }

    //生产域名数据
    private static String getDomains() {
        String[] domains = new String[]{
                "v1.go2yd.com",
                "v2.go2yd.com",
                "v3.go2yd.com",
                "v4.go2yd.com",
                "vmi.go2yd.com"
        };

        return domains[new Random().nextInt(domains.length)];
    }

    //生产Ip数据
    private static String getIps() {
        String[] ips = new String[]{
                "223.104.18.110",
                "113.101.75.194",
                "27.17.127.135",
                "183.225.139.16",
                "112.1.66.34",
                "175.148.211.190",
                "183.227.58.21",
                "59.83.198.84",
                "117.28.38.28",
                "117.59.39.169"
        };
        return ips[new Random().nextInt(ips.length)];
    }

    //生产level数据
    public static String getLevels(){
        String[] levels = new String[]{"M","E"};
        return levels[new Random().nextInt(levels.length)];
    }


}
