package manage.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC工具类
 * @author kuangjunlin
 */
public class JDBCUtil {
    static{//加载JDBC驱动程序
        try{
            String diverName= "com.mysql.cj.jdbc.Driver";
            Class.forName(diverName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        //创建数据库连接
        Connection con = null;
        try {
            //北京时间东八区，时区要设置好，不然会出现时差
        con = DriverManager.getConnection(
                "jdbc:mysql://111.230.99.115/manage"+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false",
                "root",
                "yourpassword");
        }catch (Exception e){
            e.printStackTrace();
        }
        return con; }

    public static void close(ResultSet rs, Statement statement,Connection con){
        //关闭数据库 连接
        try {
            if (rs != null) {
                rs.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try{
            if (con != null) {
                con.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
} }