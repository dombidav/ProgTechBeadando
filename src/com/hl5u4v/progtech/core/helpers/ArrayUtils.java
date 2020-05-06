package com.hl5u4v.progtech.core.helpers;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;


public class ArrayUtils {

    @NotNull
    public static <T> Iterable<T> where(@NotNull Iterable<T> items, Predicate<T> predicate) {
        ArrayList<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @NotNull
    public static <T, R> Iterable<R> select(@NotNull Iterable<T> items, Function<T, R> func) {
        ArrayList<R> result = new ArrayList<>();
        for (T item : items) {
            result.add(func.apply(item));
        }
        return result;
    }

    @SuppressWarnings("SuspiciousSystemArraycopy")
    public static <T> T concat(@NotNull T a, T b) {
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
