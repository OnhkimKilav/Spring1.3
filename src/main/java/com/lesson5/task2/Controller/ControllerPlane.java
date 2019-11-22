package com.lesson5.task2.Controller;

import com.google.gson.Gson;
import com.lesson5.task2.Model.Plane;
import com.lesson5.task2.Service.ServiceLesson5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@RestController
public class ControllerPlane {
    private ServiceLesson5 service;

    @Autowired
    public ControllerPlane(ServiceLesson5 service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/save-plane-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> saveItem(HttpServletRequest req) throws IOException {
        Plane plane = null;
        try {
            plane = (Plane) service.save(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + plane.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-plane-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findItem(HttpServletRequest req) throws IOException {
        Plane plane = null;
        try {
            plane = (Plane) service.findById(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + plane.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-plane-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> deleteItem(HttpServletRequest req) {
        try {
            service.delete(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update-plane-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> updateItem(HttpServletRequest req) throws IOException {
        try {
            service.update(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByOld-plane-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findByOldPlane(HttpServletRequest req) throws IOException {
        List<Long> planeId;
        try {
            planeId = service.findByOldPlane();
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Plane ID : " + planeId.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findRegularPlanes-plane-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findRegularPlanes(HttpServletRequest req) throws IOException {
        List<Long> planeId;
        try {
            planeId = service.findRegularPlanes(Integer.parseInt(req.getParameter("year")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Plane ID : " + planeId.toString(), HttpStatus.OK);
    }

    private Plane readValuesPostman(HttpServletRequest req) throws IOException {
        Plane plane;
        try (BufferedReader reader = req.getReader()) {
            Gson gson = new Gson();
            plane = gson.fromJson(reader, Plane.class);
        }
        return plane;
    }
}
