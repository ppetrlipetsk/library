package com.ppsdevelopment.tmcprocessor.typeslib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

/**
 * Класс, хранящий предопределенные значения типов полей таблицы. Например, id должен быть только int.
 * Чтобы не получилось иначе, это значение задается в данном классе.
 * Данные о предопределенных значениях полей загржаются из внешнего файла.
 */
public class FieldsDefaults {

    private static  HashMap<String, TableFieldsRepository> tableFields;

    static {
        tableFields =new HashMap<>();
    }

    public static boolean isFieldExists(String tableName, String fieldName){
        boolean b=false;
        TableFieldsRepository fields=tableFields.get(tableName);
        if (fields!=null) b=fields.getFields().containsKey(fieldName);
        return b;
    }

    public static FieldType getFieldType(String tableName, String fieldName){
        boolean b=false;
        TableFieldsRepository fields=tableFields.get(tableName);
        if (fields!=null&&fields.getFields().containsKey(fieldName)) return fields.getFields().get(fieldName);
        return null;
    }


    private static Properties getProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        boolean ret=true;
        properties.load(new FileInputStream(fileName));
        return properties;
    }



    public static boolean loadDefaultFields(String fileName, String tableName) throws IOException {
        Properties properties =getProperties(fileName);
        TableFieldsRepository fields = new TableFieldsRepository();
        Set<?> keys=properties.keySet();
        boolean ret=true;

        for (Object key:keys){
            String keyName=(String) key;

            if (!properties.containsKey(key)) throw new NoSuchElementException("Неверный ключ! Key="+keyName);

            String value=(String)properties.getProperty(keyName);

            if (checkFieldType(value)) {
                fields.getFields().put(keyName, FieldType.valueOf(value));
            }
        }

        tableFields.put(tableName,fields);

        return ret;
    }

    private static boolean checkFieldType(String value) {
        boolean res;
        try {
            FieldType f = FieldType.valueOf(value);
            res=true;
        }
        catch (IllegalArgumentException e){
            res=false;
        }
        return res;
    }

}




