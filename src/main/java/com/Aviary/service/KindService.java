package com.Aviary.service;

import com.Aviary.dao.KindDAO;

import java.sql.SQLException;
import java.util.List;

public class KindService    {
    private KindDAO kindDAO = new KindDAO();

    public List<String> getAll() {
        return kindDAO.getAllKinds();
    }

    public void add(String name)  {
        name = normalize(name);

        if (name.isEmpty())
            throw new RuntimeException("Tên loại không được để trống");

        if (kindDAO.existsByName(name))
            throw new RuntimeException("Loại sản phẩm đã tồn tại");
        kindDAO.addKind(name);
    }

    public void update(String oldName, String newName) {
        newName = normalize(newName);

        if (newName.isEmpty())
            throw new RuntimeException("Tên loại không hợp lệ");

        if (kindDAO.existsByName(newName))
            throw new RuntimeException("Tên loại đã tồn tại");
        kindDAO.updateKind(oldName, newName);
    }

    public void delete(String name)  {
        if (kindDAO.isUsed(name))
            throw new RuntimeException("Không thể xóa loại đang được sử dụng");
        kindDAO.deleteKind(name);
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\\s+", " ");
    }
}
