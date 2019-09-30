package com.lesson3.hometask.validate;

import com.lesson3.hometask.DAO.DAO;
import com.lesson3.hometask.Model.File;
import com.lesson3.hometask.Model.Storage;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Validate {

    public static void checkFileHasStorage(File file) throws SQLException {
        if (file.getStorage() != null)
            throw new SQLException("File " + file.getId() + " already has storage");

    }

    public static void checkFileHasStorageTo(File file, long idStorageTo) throws SQLException {
        if (file.getStorage().getId() == idStorageTo)
            throw new SQLException("File " + file.getId() + " is already use this storage " + idStorageTo);

    }

    public static void checkFileInStorageFormats(Storage storage, File file) throws SQLException {
        List<String> formats = Arrays.asList(storage.getFormatsSupported().split(", "));
        for (String format : formats) {
            if (format.equals(file.getFormat()))
                return;
        }
        throw new SQLException("Storage " + storage.getId() + " doesn't have format incoming file " + file.getId());
    }

    public static void checkMaxSizeInStorage(Storage storage, File file) throws SQLException {
        List<Integer> l = DAO.listFileSize(storage);
        long sum = 0;
        for (Integer l1 : l)
            sum += l1;

        sum += file.getSizeFile();
        if (sum >= storage.getStorageMaxSize())
            throw new SQLException("The storage " + storage.getId() + " has run out of space");

    }

    public static void checkMaxSizeInStorage(Storage storage, List<Object>  files) throws SQLException {
        List<Integer> l = DAO.listFileSize(storage);

        long sum = 0;
        for (Integer l1 : l)
            sum += l1;

        Iterator itr = files.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            sum += Integer.parseInt(String.valueOf(obj[3]));
            if (sum >= storage.getStorageMaxSize())
                throw new SQLException("The storage " + storage.getId() + " has run out of space");
        }
    }
}
