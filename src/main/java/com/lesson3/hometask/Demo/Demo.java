package com.lesson3.hometask.Demo;


import com.lesson3.hometask.Controller.Controller;
import com.lesson3.hometask.Model.Storage;
import com.lesson3.hometask.Service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        //ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*Storage storage = new Storage();
        storage.setId(1);
        storage.setFormatsSupported("pdf, docx, jpg, png");
        storage.setStorageCountry("Ukraine");
        storage.setStorageMaxSize(666L);

        File file = new File();
        file.setId(8);
        file.setName("Valik");
        file.setFormat("pdf");
        file.setSize_file(100);
        file.setStorage(null);

        Controller controller = new Controller(new Service(new DAO()));
        controller.put(storage, file);*/

        Storage storageTo = new Storage();
        storageTo.setId(3);
        storageTo.setFormatsSupported("pdf, docx, jpg, png");
        storageTo.setStorageCountry("Ukraine");
        storageTo.setStorageMaxSize(5000L);

        Storage storageFrom = new Storage();
        storageFrom.setId(2);
        storageFrom.setFormatsSupported("pdf, docx, zip, jpg");
        storageFrom.setStorageCountry("Ukraine");
        storageFrom.setStorageMaxSize(2000L);

        Controller controller = new Controller(new Service());
        controller.transferFill(storageFrom, storageTo, 2);
    }

    /*{
        "id": 1,
            "name":"Valik",
            "format":"pdf",
            "size":12,
            "storage":{
        "id":2,
                "formatsSupported":["pdf", "jpg", "txt"],
        "storageCountry":"Ukraine",
                "storageMaxSize":150
    }
    }*/
}
