package com.hl5u4v.progtech.core.Helpers;

import java.lang.reflect.Array;



public class ArrayUtils {

    /**
     * Concatenate two arrays. It also makes it possible to concatenate arrays of two different types by picking the most general type as the component type of the result.
     * Source: https://stackoverflow.com/a/80503
     * @param a First array
     * @param b Second array
     * @param <T> Type of arrays
     * @return Concatenated array
     */
    @SuppressWarnings("SuspiciousSystemArraycopy")
    public static <T> T concat(T a, T b) {
        if (!a.getClass().isArray() || !b.getClass().isArray()) {
            throw new IllegalArgumentException();
        }

        Class<?> resCompType;
        Class<?> aCompType = a.getClass().getComponentType();
        Class<?> bCompType = b.getClass().getComponentType();

        if (aCompType.isAssignableFrom(bCompType)) {
            resCompType = aCompType;
        } else if (bCompType.isAssignableFrom(aCompType)) {
            resCompType = bCompType;
        } else {
            throw new IllegalArgumentException();
        }

        int aLen = Array.getLength(a);
        int bLen = Array.getLength(b);

        @SuppressWarnings("unchecked")
        T result = (T) Array.newInstance(resCompType, aLen + bLen);
        System.arraycopy(a, 0, result, 0, aLen);
        System.arraycopy(b, 0, result, aLen, bLen);

        return result;
    }

}
