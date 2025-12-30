package com.Aviary.service;

import java.net.HttpCookie;
import java.util.OptionalInt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Util {
    public static <T> T getFromSession(HttpSession session, String name, Class<T> type){
        Object value = session.getAttribute(name);

        if(value == null) return null;

        if(!type.isInstance(value)) return null;

        return type.cast(value);
    }

    public static String getString(HttpServletRequest req, String attribute){
        if(req == null || attribute == null) return null;
        Object obj = req.getAttribute(attribute);
        if(!(obj instanceof String)) return null;
        String str = (String) obj;

        if(str.isEmpty()) return null;

        return str;
    }


    public static OptionalInt getIntOpt(HttpServletRequest req, String name) {
        Object v = req.getAttribute(name);
        if (!(v instanceof Number)) return OptionalInt.empty();
        return OptionalInt.of(((Number) v).intValue());
    }
    
    public static int getIntOrDefault(HttpServletRequest req, String name, int defaultValue) {
        return getIntOpt(req, name).orElse(defaultValue);
    }

    public static int getIntOrThrow(HttpServletRequest req, String name) {
        return getIntOpt(req, name)
                .orElseThrow(() -> new IllegalStateException(name + " is required"));
    }
}
