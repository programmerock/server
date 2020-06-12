package ar.org.centro8.curso.java.aplicaciones.connectors;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
//    private static String driver="com.mysql.cj.jdbc.Driver";
//    private static String vendor="mysql";
//    private static String server="localhost";
//    private static String port="3306";
//    private static String database="negocioWeb";
//    private static String params="?serverTimezone=UTC";
//    private static String user="root";
//    private static String pass="centro8";
//    private static String url="jdbc:"+vendor+"://"+server+":"+port+"/"+database+params;
    
    // Para correr en la base de remoteMySql
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String vendor="mysql";
    private static String server="remotemysql.com";
    private static String port="3306";
    private static String database="Va7cP5nXBW";
    private static String params="";
    private static String user="Va7cP5nXBW";
    private static String pass="7Og9AIy9iA";
    private static String url="jdbc:"+vendor+"://"+server+":"+port+"/"+database+params;
    
    private static Connection conn=null;
    private Connector(){}
    public synchronized static Connection getConnection(){
        try{
            if(conn==null || conn.isClosed()){
                try {
                    Class.forName(driver);
                    conn=DriverManager.getConnection(url, user, pass);
                } catch (Exception e) {e.printStackTrace();}
            }
        }catch (Exception e) {e.printStackTrace();}        
        return conn;
    }
    
}
