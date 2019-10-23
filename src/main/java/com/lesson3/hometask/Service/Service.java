package com.lesson3.hometask.Service;

import com.lesson3.hometask.DAO.DAO;
import com.lesson3.hometask.Model.File;
import com.lesson3.hometask.Model.Storage;
import com.lesson3.hometask.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@org.springframework.stereotype.Service
public class Service<T>{
    private T t;
    private DAO dao;

    @Autowired
    public Service() {
        this.dao = dao;
    }

    public T save(T t, int id) {
        return (T) dao.save(t);
    }

    public void delete(T t, int id) {
        dao.delete((Class) t, id);
    }

    public void update(T t) {
        dao.update(t);
    }

    public T findById(T t, int id) {
        return (T) dao.findById((Class) t, id);
    }

    public void put(Storage storage, File file) throws SQLException {
        Validate.checkFileHasStorage(file);
        Validate.checkFileInStorageFormats(storage, file);
        Validate.checkMaxSizeInStorage(storage, file);

        dao.put(storage, file);
    }

    public void delete(Storage storage, File file) {
        dao.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {
        dao.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, int id) throws SQLException {
        Validate.checkFileInStorageFormats(storageTo, (File) dao.findById(File.class, id));
        Validate.checkMaxSizeInStorage(storageTo, (File) dao.findById(File.class, id));
        Validate.checkFileHasStorageTo((File) dao.findById(File.class, id), storageTo.getId());

        dao.transferFile(storageFrom, storageTo, id);
    }
}
