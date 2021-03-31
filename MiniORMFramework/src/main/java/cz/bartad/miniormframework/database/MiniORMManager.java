package cz.bartad.miniormframework.database;

import cz.bartad.fiktivniProgram.entity.Film;
import cz.bartad.miniormframework.exception.AnnotationMissingException;
import cz.bartad.miniormframework.reflection.ObjectReflector;
import cz.bartad.miniormframework.sql.SqlBuilder;

import java.io.ObjectStreamException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MiniORMManager {
    public <T> T getEntityById(Long id, Class<T> tClass) throws Exception {
        T o=null;

        if(id == null){
            throw new IllegalArgumentException("ID nemůže výt NULL");
        }

        if(!ObjectReflector.isTable(tClass)){
            throw new AnnotationMissingException("Objekt nemá anotaci Tabulky");
        }else{
            System.out.println("Is table "+tClass.getName());
        }

        //nacitani dat z DB
        ResultSet resultSet=loadData(id,tClass);
        System.out.println(resultSet.getString("ADDRESS"));

        //nasetovat na objekt
        o=ObjectReflector.getFilledData(resultSet,tClass);

        return o;
    }

    private <T> ResultSet loadData(Long id, Class<T> tClass) throws Exception {
        String tableName=ObjectReflector.getTableName(tClass);
        List<String> tableColumns=ObjectReflector.getColumnNames(tClass);
        String idColumnName= ObjectReflector.getIdColumnName(tClass);

        String query = SqlBuilder.buildQuery(id,tableName,idColumnName,tableColumns);

        //ziskat ResultSet
        DatabaseAccess databasAccess=null;
        ResultSet resultSet=null;

        try{
            databasAccess=new DatabaseAccess();
            resultSet=databasAccess.executeQuery(query);
            databasAccess.commit();
        }catch (Exception e){
            if(databasAccess!=null)
                databasAccess.rollBack();
            throw new Exception(e);
        }finally {
            if(databasAccess!=null){
                databasAccess.disconnect();
            }
        }

        return resultSet;
    }

    public <T> void insertEntity(T object) {
        Class<?> tClass=object.getClass();
        String tableName=ObjectReflector.getTableName(tClass);
        List<String> tableColumns=ObjectReflector.getColumnNames(tClass);
        /*
        Map<String,Object> data =ObjectReflector.getObejctData(tClass);
        String query=SqlBuilder.buildInsertQuery(tableName,tableColumns,data);
        DatabaseAccess databaseAccess=new DatabaseAccess();
        databaseAccess.insert(query);
        //insert into_Movie(NAZEV_FILMU,REZISER,HLAVNI_POSTAVA)_VALUES('','',0)*/
    }
}
