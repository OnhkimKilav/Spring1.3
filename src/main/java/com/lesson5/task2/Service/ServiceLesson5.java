package com.lesson5.task2.Service;

import com.lesson5.task2.DAO.DAOLesson5;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceLesson5<T> {
    private DAOLesson5 dao;

    @Autowired
    public ServiceLesson5(DAOLesson5 dao) {
        this.dao = dao;
    }

    public T save(T t) {
        return (T) dao.save(t);
    }

    public void delete(Long id) {
        dao.delete(id);
    }


    public void update(T t) {
        dao.update(t);
    }

    public T findById(Long id) {
        return (T) dao.findById(id);
    }

    public List findFlightByDate(String date) {
        return dao.flightsByDate(date);
    }

    public List findFlightByDate(String fromDate, String toDate) {
        return dao.flightsByDate(fromDate, toDate);
    }

    public List flightsFromCity(String city) {
        return dao.flightsFromCity(city);
    }

    public List flightsToCity(String city) {
        return dao.flightsToCity(city);
    }

    public List flightsByModelPlane(String model) {
        return dao.flightsByModelPlane(model);
    }

    public List findByOldPlane() {
        return dao.findByOldPlane();
    }

    public List findRegularPassenger(int year) {
        return dao.findRegularPassenger(year);
    }

    public List findRegularPlanes(int year) {
        return dao.regularPlanes(year);
    }

    public List findMostPopularCityTo() {
        return dao.mostPopularTo();
    }

    public List findMostPopularCityFrom() {
        return dao.mostPopularFrom();
    }
}
