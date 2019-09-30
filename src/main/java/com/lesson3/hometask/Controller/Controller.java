package com.lesson3.hometask.Controller;

import com.lesson3.hometask.Model.File;
import com.lesson3.hometask.Model.Storage;
import com.lesson3.hometask.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
@Component
public class Controller {
    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    public void put(Storage storage, File file) throws SQLException {
        service.put(storage, file);
    }

    public void delete(Storage storage, File file){
        service.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo){
        service.transferAll(storageFrom, storageTo);
    }

    public void transferFill(Storage storageFrom, Storage storageTo, int id) throws SQLException {
        service.transferFile(storageFrom, storageTo, id);
    }
}
