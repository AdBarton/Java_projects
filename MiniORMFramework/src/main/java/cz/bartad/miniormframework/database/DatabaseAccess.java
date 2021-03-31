package cz.bartad.miniormframework.database;

import java.sql.*;
import java.util.Properties;

public class DatabaseAccess {

    private Connection connection=null;
    private PropertiesReader propertiesReader=new PropertiesReader();

    DatabaseAccess() throws Exception {
        String user = propertiesReader.getProperty(PropertiesReader.DB_USR);
        String psw = propertiesReader.getProperty(PropertiesReader.DB_PSW);
        String driver = propertiesReader.getProperty(PropertiesReader.DB_DRIVER);
        String url = propertiesReader.getProperty(PropertiesReader.DB_URL);
        connect(user, psw, driver, url);
    }

    private void connect(String user, String psw,String driver,String url) throws Exception {
        Class.forName(driver);
        connection= DriverManager.getConnection(url,user,psw);

        if(connection!=null){
            connection.setAutoCommit(false);
        }else{
            throw new SQLException("Connection problem.");
        }
    }

    public void disconnect(){
        try{
            connection.close();
        }catch (Exception e){
            //LOG.error("Can not close connection.");
            System.err.println("Can not close connection.");
        }
    }

    public void commit(){
        try{
            connection.commit();
        }catch (Exception e){
            //LOG.error("Can not commit transaction in connection");
            System.err.println("Can not commit transaction in connection");
        }
    }

    public void rollBack(){
        try{
            connection.rollback();
        }catch (Exception e){
            //LOG.error("Can not roll back transaction in connection");
            System.err.println("Can not roll back transaction in connection");
        }
    }

    public Connection getConn(){
        return connection;
    }


    public ResultSet executeQuery(String query) throws Exception {
        Statement statement=getConn().createStatement();
        ResultSet resultSet= statement.executeQuery(query);
        resultSet.close();
        return resultSet;
    }
}
