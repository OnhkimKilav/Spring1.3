package com.lesson5.task2.DAO;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class DAOLesson5<T> {
    protected Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public T save(T t) {
        entityManager.persist(t);
        return t;
    }

    public T findById(Long id) {
        T t = entityManager.find(entityClass, id);
        return t;
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public void update(T t) {
        entityManager.merge(t);
    }

    public List flightsByDate(String date) {
        Query query = entityManager.createNativeQuery("select ID from FLIGHT where DATE_FLIGHT=TO_DATE(?, 'YYYY-MM-DD')").setParameter(1, date);
        query.executeUpdate();
        return query.getResultList();
    }

    public List flightsByDate(String fromDate, String toDate) {
        Query query = entityManager.createNativeQuery("SELECT ID FROM FLIGHT WHERE DATE_FLIGHT BETWEEN TO_DATE (?, 'YYYY-MM-DD') " +
                "AND TO_DATE (?, 'YYYY-MM-DD')").setParameter(1, fromDate).setParameter(2, toDate);
        query.executeUpdate();
        return query.getResultList();
    }

    public List flightsFromCity(String city) {
        Query query = entityManager.createNativeQuery("SELECT ID FROM FLIGHT WHERE CITY_FROM = ?").setParameter(1, city);
        query.executeUpdate();
        return query.getResultList();
    }

    public List flightsToCity(String city) {
        Query query = entityManager.createNativeQuery("SELECT ID FROM FLIGHT WHERE CITY_TO = ?").setParameter(1, city);
        query.executeUpdate();
        return query.getResultList();
    }

    public List flightsByModelPlane(String model) {
        Query query = entityManager.createNativeQuery("SELECT FLIGHT.ID FROM FLIGHT JOIN PLANE ON FLIGHT.PLANE_ID = PLANE.ID " +
                "WHERE PLANE.MODEL = ?").setParameter(1, model);
        query.executeUpdate();
        return query.getResultList();
    }

    public List findByOldPlane() {
        Query query = entityManager.createNativeQuery("select ID From PLANE Where extract(year from sysdate) - extract(year from YEAR_PRODUCED)>20");
        query.executeUpdate();
        return query.getResultList();
    }

    public List findRegularPassenger(int year) {
        Query query = entityManager.createNativeQuery("SELECT\n" +
                "    *\n" +
                "FROM\n" +
                "    passenger\n" +
                "WHERE\n" +
                "    EXISTS (\n" +
                "        SELECT\n" +
                "            *\n" +
                "        FROM\n" +
                "            flight_passenger fp\n" +
                "            JOIN flight f ON fp.flight_id = f.id\n" +
                "        WHERE\n" +
                "            fp.passenger_id = passenger.id and\n" +
                "            EXTRACT(YEAR FROM f.date_flight) = ?\n" +
                "        GROUP BY\n" +
                "            fp.passenger_id\n" +
                "        HAVING\n" +
                "            COUNT(f.id) >= 25\n" +
                "    )").setParameter(1, year);
        query.executeUpdate();
        return query.getResultList();
    }

    public List regularPlanes(int year) {
        Query query = entityManager.createNativeQuery("SELECT\n" +
                "    *\n" +
                "FROM\n" +
                "    plane p\n" +
                "WHERE\n" +
                "    p.id = (\n" +
                "        SELECT\n" +
                "            f.plane_id\n" +
                "        FROM\n" +
                "            flight f\n" +
                "            JOIN flight_passenger fp ON f.id = fp.flight_id\n" +
                "        WHERE\n" +
                "            fp.flight_id = f.id and\n" +
                "            EXTRACT(YEAR FROM f.date_flight) = ?\n" +
                "        GROUP BY\n" +
                "            f.plane_id\n" +
                "        HAVING\n" +
                "            COUNT(f.plane_id) >= 300\n" +
                "    )").setParameter(1, year);
        query.executeUpdate();
        return query.getResultList();
    }

    public List mostPopularTo() {
        Query query = entityManager.createNativeQuery("SELECT\n" +
                "    *\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT\n" +
                "            f.city_to\n" +
                "        FROM\n" +
                "            flight f\n" +
                "            JOIN flight_passenger fp ON f.id = fp.flight_id\n" +
                "        WHERE\n" +
                "            fp.flight_id = f.id\n" +
                "        GROUP BY\n" +
                "            f.city_to\n" +
                "        ORDER BY\n" +
                "            COUNT(fp.flight_id) DESC\n" +
                "    )\n" +
                "WHERE\n" +
                "    ROWNUM <= 10");
        query.executeUpdate();
        return query.getResultList();
    }

    public List mostPopularFrom() {
        Query query = entityManager.createNativeQuery("SELECT\n" +
                "    *\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT\n" +
                "            f.city_from\n" +
                "        FROM\n" +
                "            flight f\n" +
                "            JOIN flight_passenger fp ON f.id = fp.flight_id\n" +
                "        WHERE\n" +
                "            fp.flight_id = f.id\n" +
                "        GROUP BY\n" +
                "            f.city_from\n" +
                "        ORDER BY\n" +
                "            COUNT(fp.flight_id) DESC\n" +
                "    )\n" +
                "WHERE\n" +
                "    ROWNUM <= 10");
        query.executeUpdate();
        return query.getResultList();
    }
}
