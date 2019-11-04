package com.lesson5;

import com.google.gson.Gson;
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
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save-item-lesson5", produces = "text/plain")
    public ResponseEntity<String> saveItem(HttpServletRequest req) throws IOException {
        Item item = null;
        try {
            item = itemService.save(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + item.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-item-lesson5", produces = "text/plain")
    public ResponseEntity<String> findItem(HttpServletRequest req) throws IOException {
        Item item = null;
        try {
            item = itemService.findById(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + item.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-item-lesson5", produces = "text/plain")
    public ResponseEntity<String> deleteItem(HttpServletRequest req) {
        try {
            itemService.delete(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-item-by-name-lesson5", produces = "text/plain")
    public ResponseEntity<String> deleteItemByName(HttpServletRequest req) {
        try {
            itemService.deleteByName(req.getParameter("name"));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update-item-lesson5", produces = "text/plain")
    public ResponseEntity<String> updateItem(HttpServletRequest req) throws IOException {
        try {
            itemService.update(readValuesPostman(req));
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