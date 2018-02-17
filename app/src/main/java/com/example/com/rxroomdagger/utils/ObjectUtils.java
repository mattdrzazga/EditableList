package com.example.com.rxroomdagger.utils;

public class ObjectUtils {
    public static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}
