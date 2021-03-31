package cz.bartad.miniormframework.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PropertiesReader {
    public static final String DB_URL="db.url";
    public static final String DB_DRIVER="db.driver";
    public static final String DB_USR="db.user";
    public static final String DB_PSW="db.psw";

    public String getProperty(String property) throws Exception{
        String filename="miniorm.properties";

        java.util.Properties properties=new java.util.Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream(filename);
        if(input==null){
            throw new Exception("Could not find miniorm.properties file");
        }
        properties.load(input);

        String propertyValue=properties.getProperty(property);
        if(propertyValue!= null && !propertyValue.isEmpty()){
            return propertyValue;
        }else{
            throw new Exception("property "+property+" not specified in miniorm.properties file");
        }
    }
}
