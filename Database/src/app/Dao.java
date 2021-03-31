package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {
    private Connection connection=null;
    private PropertiesReader propertiesReader=new PropertiesReader();

    Dao() throws SQLException{
        String user=propertiesReader.getProperty(PropertiesReader.ORACLE_USER);
        String psw=propertiesReader.getProperty(PropertiesReader.ORACLE_PSW);
        connect(user,psw);
    }

    private void connect(String user, String psw) throws SQLException{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e){
            e.printStackTrace();
        }
        connection=DriverManager.getConnection(getConnectionUrl(),user,psw);

        if(connection!=null){
            connection.setAutoCommit(false);
        }else{
            throw new SQLException("Connection problem.");
        }
    }

    private String getConnectionUrl(){
        String host=propertiesReader.getProperty(PropertiesReader.ORACLE_HOST);
        String port=propertiesReader.getProperty(PropertiesReader.ORACLE_PORT);
        String dbname=propertiesReader.getProperty(PropertiesReader.ORACLE_DBNAME);

        return String.format("jdbc:oracle:thin:@%s:%s:%s",host,port,dbname);
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

}
