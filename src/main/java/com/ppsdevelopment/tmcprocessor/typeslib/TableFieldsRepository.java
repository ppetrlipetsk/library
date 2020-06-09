package com.ppsdevelopment.tmcprocessor.typeslib;

import java.util.HashMap;

/**
 * Класс предоставляет механизм хранения набора полей
 */

public class TableFieldsRepository {

    private HashMap<String, FieldType> fields;

    public TableFieldsRepository() {
        fields =new HashMap<>();
    }

    public  HashMap<String, FieldType> getFields() {
        return fields;
    }

    public  void setFields(HashMap<String, FieldType> fields) {
        this.fields = fields;
    }

    public void put(String key, FieldType value){
        if (fields!=null) fields.put(key, value);
    }

    public FieldType get(String key){
        if ((fields!=null)&&(fields.containsKey(key)))
            return fields.get(key);
        else
            return null;
    }

}
