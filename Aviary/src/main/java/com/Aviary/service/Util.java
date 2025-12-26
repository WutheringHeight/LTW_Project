package com.Aviary.service;

import java.net.HttpCookie;

import jakarta.servlet.http.HttpSession;

public class Util {
    public static <T> T getFromSession(HttpSession session, String name, Class<T> type){
        Object value = session.getAttribute(name);

        if(value == null) return null;

        if(!type.isInstance(value)) return null;

        return type.cast(value);
    }
}
