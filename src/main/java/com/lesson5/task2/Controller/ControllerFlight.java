package com.lesson5.task2.Controller;

import com.google.gson.Gson;
import com.lesson5.task2.Model.Flight;
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
public class ControllerFlight {
    private ServiceLesson5 service;

    @Autowired
    public ControllerFlight(ServiceLesson5 service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/save-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> saveItem(HttpServletRequest req) throws IOException {
        Flight flight = null;
        try {
            flight = (Flight) service.save(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + flight.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findItem(HttpServletRequest req) throws IOException {
        Flight flight = null;
        try {
            flight = (Flight) service.findById(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok : " + flight.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> deleteItem(HttpServletRequest req) {
        try {
            service.delete(Long.parseLong(req.getParameter("id")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> updateItem(HttpServletRequest req) throws IOException {
        try {
            service.update(readValuesPostman(req));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByDate-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findFlightByDate(HttpServletRequest req) throws IOException {
        List<Long> flights;
        try {
            flights = service.findFlightByDate((req.getParameter("dateFlight")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flights.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findFromDateToDate-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findFlightFromDateToDate(HttpServletRequest req) throws IOException {
        List<Long> flights;
        try {
            flights = service.findFlightByDate((req.getParameter("fromDate")),(req.getParameter("toDate")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flights.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findFromCity-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findFlightFromCity(HttpServletRequest req) throws IOException {
        List<Long> flights;
        try {
            flights = service.flightsFromCity((req.getParameter("cityFrom")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flights.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findToCity-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findFlightToCity(HttpServletRequest req) throws IOException {
        List<Long> flights;
        try {
            flights = service.flightsToCity((req.getParameter("cityTo")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flights.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByModelPlane-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findFlightByModelPlane(HttpServletRequest req) throws IOException {
        List<Long> flightsId;
        try {
            flightsId = service.flightsByModelPlane((req.getParameter("model")));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flightsId.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findMostPopularCityTo-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findMostPopularCityTo(HttpServletRequest req) throws IOException {
        List<Long> flightsId;
        try {
            flightsId = service.findMostPopularCityTo();
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flightsId.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findMostPopularCityFrom-flight-lesson5-task2", produces = "text/plain")
    public ResponseEntity<String> findMostPopularCityFrom(HttpServletRequest req) throws IOException {
        List<Long> flightsId;
        try {
            flightsId = service.findMostPopularCityFrom();
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, null);
        }
        return new ResponseEntity<String>("Flight with ID : " + flightsId.toString(), HttpStatus.OK);
    }

    private Flight readValuesPostman(HttpServletRequest req) throws IOException {
        Flight flight;
        try (BufferedReader reader = req.getReader()) {
            Gson gson = new Gson();
            flight = gson.fromJson(reader, Flight.class);
        }
        return flight;
    }

}
