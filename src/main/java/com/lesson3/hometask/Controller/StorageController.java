package com.lesson3.hometask.Controller;

import com.google.gson.Gson;
import com.lesson3.hometask.Model.Storage;
import com.lesson3.hometask.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class StorageController{
    private Service service;

    @Autowired
    public StorageController(Service service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/storageSave")
    public
    String save(HttpServletRequest req) throws IOException {
        return service.save(readValuesPostman(req), Integer.parseInt(req.getParameter("id"))).toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/storageFind")
    public
    String findById(HttpServletRequest req) throws IOException {
        return service.findById(Storage.class, Integer.parseInt(req.getParameter("id"))).toString();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/storageDelete")
    public
    String delete(HttpServletRequest req) throws IOException {
        service.delete(Storage.class, Integer.parseInt(req.getParameter("id")));
        return service.findById(Storage.class, Integer.parseInt(req.getParameter("id"))).toString();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/storageUpdate")
    public
    String update(HttpServletRequest req) throws IOException {
        service.update(readValuesPostman(req));
        return readValuesPostman(req).toString();
    }

    private Storage readValuesPostman(HttpServletRequest req) throws IOException {
        Storage storage;
        try(BufferedReader reader = req.getReader()) {
            Gson gson = new Gson();
            storage = gson.fromJson(reader, Storage.class);
        }
        return storage;
    }
}
