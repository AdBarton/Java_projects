package app;

import java.sql.*;

public class DatabaseConnect {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
        String user="adrian";
        String password="heslo123";
        String host="localhost";
        String port="3306";
        String dbname="databasename";
        Connection connection = null;
        Statement statement=null;
        long id=101;

        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?characterEncoding=UTF-8",user,password);
            connection.setAutoCommit(false);

            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from Osoba where id=1");

            String query="Select * from Osoba where id ="+id;
            String query2=String.format("Select * from Osoba where id = %d",id);
            StringBuilder query3=new StringBuilder("Select * from Osoba where id= ").append(id);

            //PreparedStatement předchází SQL-Injection
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT  into Osoba(NAME,AGE,BIRTH_DATE) VALUES (?,?,?)");
            preparedStatement.setString(1,"Adrian");
            preparedStatement.setInt(2,20);
            preparedStatement.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.executeQuery();

            while (resultSet.next()){
                String name=resultSet.getString(1);
                int vek=resultSet.getInt("AGE");
            }
            connection.commit();
        }catch (Exception e){
            try{
                connection.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            if(statement!=null)
                statement.close();
            connection.close();
        }

    }
}
