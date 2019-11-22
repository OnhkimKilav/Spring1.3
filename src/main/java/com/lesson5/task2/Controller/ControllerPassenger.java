package com.lesson5.task2.Controller;

import com.google.gson.Gson;
import com.lesson5.task2.Model.Passenger;
import com.lesson5.task2.Service.ServiceLesson5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@RestController
public class ControllerPassenger {
    private ServiceLesson5 service;

    @Autowired
    public ControllerPassenger(ServiceLesson5 service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/save-passenger-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> saveItem(HttpServletRequest req) throws IOException {
        Passenger passenger = null;
        try {
            passenger = (Passenger) service.save(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + passenger.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-passenger-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findItem(HttpServletRequest req) throws IOException {
        Passenger passenger = null;
        try {
            passenger = (Passenger) service.findById(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + passenger.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-passenger-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> deleteItem(HttpServletRequest req) {
        try {
            service.delete(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update-passenger-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> updateItem(HttpServletRequest req) throws IOException {
        try {
            service.update(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findRegularPassenger-passenger-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findRegularPassenger(HttpServletRequest req) throws IOException {
        List<Long> passengerId;
        try {
            passengerId = service.findRegularPassenger(Integer.parseInt(req.getParameter("year")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Plane ID : " + passengerId.toString(), HttpStatus.OK);
    }

    private Passenger readValuesPostman(HttpServletRequest req) throws IOException {
        Passenger passenger;
        try (BufferedReader reader = req.getReader()) {
            Gson gson = new Gson();
            passenger = gson.fromJson(reader, Passenger.class);
        }
        return passenger;
    }
}
