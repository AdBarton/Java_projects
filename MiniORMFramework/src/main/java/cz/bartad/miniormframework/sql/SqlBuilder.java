package cz.bartad.miniormframework.sql;

import java.util.List;

public class SqlBuilder {
    public static String buildQuery(Long id, String tableName, String idColumnName, List<String> tableColumns) {
        StringBuilder stringBuilder=new StringBuilder();
        for(String s:tableColumns){
            stringBuilder.append(s).append(",");
        }
        String tableColumnsQuery=stringBuilder.toString().substring(0,stringBuilder.lastIndexOf(","));

        return String.format("Select %s from %s where %s=%d",tableColumnsQuery,tableName,idColumnName,id);
    }
}
