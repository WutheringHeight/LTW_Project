package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class KindDAO {

    private final Jdbi jdbi = JDBIProvider.get();

    public List<String> getAllKinds() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT name FROM kind ORDER BY name").mapTo(String.class).list());
    }

    public void addKind(String name) {
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO kind(name) VALUES (:name)").bind("name", name).execute());
    }

    public void updateKind(String oldName, String newName) {
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE kind SET name = :newName WHERE name = :oldName").bind("newName", newName).bind("oldName", oldName).execute());
    }

    public void deleteKind(String name) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM kind WHERE name = :name").bind("name", name).execute());
    }
    public boolean existsByName(String name) {
        Integer count = jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM kind WHERE name = :name")
                        .bind("name", name)
                        .mapTo(Integer.class)
                        .one()
        );
        return count > 0;
    }
    public boolean isUsed(String name) {
        Integer count = jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM product WHERE name = :name")
                        .bind("name", name)
                        .mapTo(Integer.class)
                        .one()
        );
        return count > 0;
    }


}
