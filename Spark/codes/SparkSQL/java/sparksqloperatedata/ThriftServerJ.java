package sparksqloperatedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ThriftServerJ {

    public static void main(String[] args) throws Exception{

        Class.forName("org.apache.hive.jdbc.HiveDriver");

        Connection connection = DriverManager.getConnection("jdbc:hive2://localhost:10000","willhope","");

        PreparedStatement pstmt = connection.prepareStatement("select empno,ename from emp");

        ResultSet res = pstmt.executeQuery();

        while ((res.next())){
            System.out.println("empno:"+res.getInt("empno") + ",ename:" + res.getString("ename"));
        }

        res.close();
        pstmt.close();
        connection.close();

    }

}
