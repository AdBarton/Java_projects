package app;

import app.utils.Osoba;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;

public class OsobaDao extends Dao{
    public OsobaDao() throws SQLException{
        super();
    }

    public long create(Osoba osoba) throws Exception{
        long id=-1L;
        PreparedStatement statement=null;
        try{
            String[] generatedId={"ID"};
            statement=getConn().prepareStatement("insert  into Osoba (NAME, AGE,BIRTH_DATE) values (?,?,?)");
            statement.setString(1,osoba.getFirtName());
            statement.setInt(2,osoba.getAge());
            statement.setDate(3,new java.sql.Date(osoba.getBirthDate().getTime()));
            statement.execute();

            try(ResultSet generatedColumns=statement.getGeneratedKeys()){
                if(generatedColumns.next()){
                    id=generatedColumns.getLong(1);
                }else {
                    throw new SQLException("Vytvoreni osoby se nepodařilo.");
                }
            }
        }catch (Exception e){
            throw new Exception(e);
        }finally {
            try{
                if(statement!=null)
                    statement.close();
            }catch (SQLException e){
                System.err.println("..");
            }

        }
        return id;
    }

}
