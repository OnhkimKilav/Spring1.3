package com.lesson5.task2.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "FLIGHT")
public class Flight {

    @Id
    @SequenceGenerator(name = "FLIGHT_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLIGHT_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLANE_ID", nullable = false )
    private Plane plane;

    @ManyToMany
    @JoinTable(name="FLIGHT_PASSENGER",
            joinColumns = @JoinColumn(name="PASSENGER_ID", referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn(name="FLIGHT_ID", referencedColumnName="ID")
    )
    private Set<Passenger> passengers;

    @Column(name = "DATE_FLIGHT")
    private Date dateFlight;

    @Column(name = "CITY_FROM")
    private String cityFrom;

    @Column(name = "CITY_TO")
    private String cityTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", passengers=" + passengers +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}
