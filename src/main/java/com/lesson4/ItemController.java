package com.lesson4;

import com.google.gson.Gson;
import com.lesson3.hometask.Model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class ItemController {
    private ItemDAO dao;

    @Autowired
    public ItemController(ItemDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save-item", produces = "text/plain")
    public ResponseEntity<String> saveItem(HttpServletRequest req) throws IOException {
        Item item = null;
        try {
            item = dao.save(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + item.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-item", produces = "text/plain")
    public ResponseEntity<String> findItem(HttpServletRequest req) throws IOException {
        Item item = null;
        try {
            item = dao.findById(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + item.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-item", produces = "text/plain")
    public ResponseEntity<String> deleteItem(HttpServletRequest req) {
        try {
            dao.delete(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update-item", produces = "text/plain")
    public ResponseEntity<String> updateItem(HttpServletRequest req) throws IOException {
        try {
            dao.update(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    private Item readValuesPostman(HttpServletRequest req) throws IOException {
        Item item;
        try (BufferedReader reader = req.getReader()) {
            Gson gson = new Gson();
            item = gson.fromJson(reader, Item.class);
        }
        return item;
    }
}
