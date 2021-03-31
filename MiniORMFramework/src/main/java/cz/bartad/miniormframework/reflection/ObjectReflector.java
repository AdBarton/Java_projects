package cz.bartad.miniormframework.reflection;

import cz.bartad.miniormframework.annotation.Identifier;
import cz.bartad.miniormframework.annotation.Table;
import cz.bartad.miniormframework.annotation.TableColumn;
import cz.bartad.miniormframework.exception.MissingIdException;
import cz.bartad.miniormframework.exception.MissingTableColumnAnnotationException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectReflector {

    public static <T> boolean isTable(Class<T> tClass) {
        return tClass.isAnnotationPresent(Table.class);
    }

    public static <T> String getTableName(Class<T> tClass) {
        return tClass.getAnnotation(Table.class).value();
    }

    public static <T> List<String> getColumnNames(Class<T> tClass) {
        List<String> columns=new ArrayList<>();
        for (Field f :tClass.getDeclaredFields()) {
            if(f.isAnnotationPresent(TableColumn.class)){
                TableColumn tableColumn=f.getAnnotation(TableColumn.class);
                columns.add(tableColumn.value());
                //System.out.println("Table Column: " +tableColumn.value());
            }
        }
        return columns;
    }

    public static <T> String getIdColumnName(Class<T> tClass) {
        String idColumnName=null;
        for(Field field:tClass.getDeclaredFields()){
            if(field.isAnnotationPresent(Identifier.class)){
                if(field.isAnnotationPresent(TableColumn.class)){
                    idColumnName=field.getAnnotation(TableColumn.class).value();
                }else{
                    throw new MissingTableColumnAnnotationException("Pri hledani ID nastala chyba");
                }
            }
        }
        if(idColumnName==null)
            throw new MissingIdException("Chyba anotace ID v entite" + tClass.getName());
        System.err.println("Table column s ID: "+idColumnName);
        return idColumnName;
    }

    public static <T> T getFilledData(ResultSet resultSet, Class<T> tClass) throws Exception {
        T object=null;
        object = tClass.newInstance();
        for(Field f:object.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.isAnnotationPresent(TableColumn.class)){
                String typeElement=f.getType().getName();
                String columnName= f.getAnnotation(TableColumn.class).value();
                if(typeElement.equals(String.class.getName())){
                    f.set(object,resultSet.getString(columnName));
                }else if(typeElement.equals(Long.class.getName())){
                    f.set(object,resultSet.getLong(columnName));
                }else if(typeElement.equals(Integer.class.getName())){
                    f.set(object,resultSet.getInt(columnName));
                }
            }
        }
        return object;
    }
}
