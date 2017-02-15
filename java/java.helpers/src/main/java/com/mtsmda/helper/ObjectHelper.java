package com.mtsmda.helper;

/**
 * Created by dminzat on 8/11/2016.
 */
public class ObjectHelper {

    public static <T> void objectIsNullThrowException(T t){
        if (objectIsNull(t)) {
            throw new RuntimeException("input object is null!");
        }
    }

    public static <T> boolean objectIsNull(T t){
        return null == t;
    }

    public static <T> boolean objectIsNotNull(T t){
        return !objectIsNull(t);
    }

    public static <T> boolean isSameObjects(T firstObj, T secondObj){
        return firstObj == secondObj;
    }

    public static <T> boolean isNotSameObjects(T firstObj, T secondObj){
        return !isSameObjects(firstObj, secondObj);
    }

    public static <T> boolean isEqualsObjects(T firstObj, T secondObj){
        return firstObj.equals(secondObj);
    }

    public static <T> boolean isNotEqualsObjects(T firstObj, T secondObj){
        return !isEqualsObjects(firstObj, secondObj);
    }

}