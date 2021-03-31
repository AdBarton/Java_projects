package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PropertiesReader {
    public static final String ORACLE_HOST="oracledb.host";
    public static final String ORACLE_PORT="oracledb.port";
    public static final String ORACLE_DBNAME="oracledb.dbname";
    public static final String ORACLE_USER="oracledb.user";
    public static final String ORACLE_PSW="oracledb.psw";

    public String getProperty(String property){
        String filename="application.properties";
        String propertyValue="";
        java.util.Properties properties=new java.util.Properties();
        InputStream input = null;

        try{
            input=getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null){
                //Log.error("Sorry, unable to find "+filename);
                return  "Sorry, unable to find "+filename;
            }
            properties.load(input);

            propertyValue=properties.getProperty(property);
            if(propertyValue!= null && !propertyValue.isEmpty()){
                return propertyValue;
            }else{
                //Log.error("property "+property+" not specified in config.properties file");
                return null;
            }
        } catch (FileNotFoundException f){
            //Log.error("File config.properties file nod found ",f);
            System.err.println("File config.properties file nod found "+f);
        }catch (IOException e){
            //Log.error("IOException ",e);
            System.err.println("IOException "+e);
        }
        return propertyValue;
    }
}
