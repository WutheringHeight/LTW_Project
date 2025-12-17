package com.Aviary.components;

import org.jdbi.v3.core.Jdbi;

public class JDBIProvider {
    private static Jdbi JDBI;

    static{
        JDBI = Jdbi.create(
        "jdbc:mysql://localhost:3306/mydb",
        "root",
        "");
    }
    
    public JDBIProvider(){

    }
    public static Jdbi get(){return JDBI;}
}
