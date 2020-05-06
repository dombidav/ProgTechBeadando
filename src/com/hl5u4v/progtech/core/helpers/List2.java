package com.hl5u4v.progtech.core.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class List2<T> extends ArrayList<T> {
    public List2() {
        super();
    }

    public List2(T[] array) {
        super(Arrays.asList(array));
    }

    public List2(Iterable<T> list) {
        super((Collection<? extends T>) list);
    }

    public T firstOr(T failSafe) {
        if (super.size() > 0)
            return get(0);
        else
            return failSafe;
    }

    public T lastOr(T failSafe) {
        if (size() > 0)
            return get(super.size() - 1);
        else
            return failSafe;
    }

    public T get(int index, T failSafe) {
        if (index > super.size() || index < 0) {
            return failSafe;
        }
        return super.get(index);
    }

    public boolean any(Predicate<T> predicate) {
        for (var item : subList(0, super.size())) {
            if (predicate.test(item))
                return true;
        }
        return false;
    }

    public boolean all(Predicate<T> predicate) {
        return !any(predicate);
    }

    public List2<T> take(int num) {
        var result = new ArrayList<T>();
        for (int i = 0; i < num; i++) {
            result.add(get(i));
        }
        return new List2<>(result);
    }

    public List2<T> skip(int num) {
        var result = new ArrayList<T>();
        for (int i = num; i < size(); i++) {
            result.add(get(i));
        }
        return new List2<>(result);
    }

    public List2<T> where(Predicate<T> predicate) {
        var result = new ArrayList<T>();
        for (var item : this) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return new List2<>(result);
    }

    public <R> List2<R> select(Function<T, R> function) {
        var result = new List2<R>();
        for (var item : this) {
            result.add(function.apply(item));
        }
        return result;
    }
}
