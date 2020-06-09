package com.ppsdevelopment.tmcprocessor.typeslib;

import java.util.HashMap;

/**
 * Класс определяет значения по умолчанию для полей данных
 * Устанавливаются следующие свойства:
 * - Строковое представление типа поля для подстановки в запросы к БД
 * - Строковая маска для формирования строки значений при формировании запроса к БД
 * - Значения по умолчанию для каждого типа поля.
 * - Приоритет типов, требуется при определении типа столбца во время сканирования таблицы *.XLSX
 *  Предоставляет статические методы для получения значений
 */
public class FieldTypeDefines {

    public static HashMap<FieldType,String> fieldTypeStr =new HashMap<>();
    public static HashMap<FieldType,String> fieldTypeValueMask =new HashMap<>();
    public static HashMap<FieldType,String> fieldTypeDefaultValue =new HashMap<>();
    public static HashMap<FieldType,Integer> fieldTypePriority=new HashMap<>(); // Приоритеты типов. Самый высокий=0;


    static{
        fieldTypeStr.put(FieldType.DATETYPE,"[date]");
        fieldTypeStr.put(FieldType.DECIMALTYPE,"[decimal](16, 10)");
        fieldTypeStr.put(FieldType.FLOATTYPE,"[float]");
        fieldTypeStr.put(FieldType.STRINGTYPE,"[varchar](1000)");
        fieldTypeStr.put(FieldType.LONGSTRINGTYPE,"[varchar](5000)");
        fieldTypeStr.put(FieldType.INTTYPE,"[int]");
        fieldTypeStr.put(FieldType.BIGINTTYPE,"[bigint]");

        fieldTypeValueMask.put(FieldType.DATETYPE,"'@value@'");
        fieldTypeValueMask.put(FieldType.FLOATTYPE,"@value@");
        fieldTypeValueMask.put(FieldType.DECIMALTYPE,"@value@");
        fieldTypeValueMask.put(FieldType.STRINGTYPE,"'@value@'");
        fieldTypeValueMask.put(FieldType.LONGSTRINGTYPE,"'@value@'");
        fieldTypeValueMask.put(FieldType.INTTYPE,"@value@");
        fieldTypeValueMask.put(FieldType.BIGINTTYPE,"@value@");

        fieldTypeDefaultValue.put(FieldType.DATETYPE,"01.01.2000");
        fieldTypeDefaultValue.put(FieldType.FLOATTYPE,"0");
        fieldTypeDefaultValue.put(FieldType.DECIMALTYPE,"0");
        fieldTypeDefaultValue.put(FieldType.STRINGTYPE,"");
        fieldTypeDefaultValue.put(FieldType.LONGSTRINGTYPE,"");
        fieldTypeDefaultValue.put(FieldType.INTTYPE,"0");
        fieldTypeDefaultValue.put(FieldType.BIGINTTYPE,"0");

        fieldTypePriority.put(FieldType.DATETYPE,6);
        fieldTypePriority.put(FieldType.DECIMALTYPE,3);
        fieldTypePriority.put(FieldType.FLOATTYPE,2);
        fieldTypePriority.put(FieldType.STRINGTYPE,1);
        fieldTypePriority.put(FieldType.LONGSTRINGTYPE,0);
        fieldTypePriority.put(FieldType.INTTYPE,5);
        fieldTypePriority.put(FieldType.BIGINTTYPE,4);
    }

    /**
     * Возвращает строку представления типа поля при запросе к БД.
     * @return
     */
    public static String getFieldTypeStr(FieldType t){
        return fieldTypeStr.get(t);
    }

    /**
     * Возвращает строку, содержащую маску для формирования подстановки значения поля в запросе к БД.
     * @param t - тип поля
     * @return строку, содержащую маску для формирования подстановки значения поля в запросе к БД.
     */
    public static String getFieldMaskStrByType(FieldType t){
        return fieldTypeValueMask.get(t);
    }

    /**
     * Возвращает значение по умолчанию для поля заданного типа
     * @param t - тип поля
     * @return - значение по умолчанию для поля заданного типа
     */
    public static String getDefaultValueForType(FieldType t){
        return fieldTypeDefaultValue.get(t);
    }


    /**
     * Возвращает приоритет типа поля.
     * @return приоритет типа поля
     */
    public static HashMap<FieldType, Integer> getFieldTypePriority() {
        return fieldTypePriority;
    }

}
