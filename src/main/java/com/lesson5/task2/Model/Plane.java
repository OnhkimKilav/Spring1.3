package com.lesson5.task2.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLANE")
public class Plane {

    @Id
    @SequenceGenerator(name = "PLANE_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANE_SEQ")
    private Long id;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "CODE")
    private String code;

    @Column(name = "YEAR_PRODUCED")
    private Date yearProduced;

    @Column(name = "AVG_FUEL_CONSUMPTION")
    private Double avgFuelConsumption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(Date yearProduced) {
        this.yearProduced = yearProduced;
    }

    public Double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(Double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", yearProduced=" + yearProduced +
                ", avgFuelConsumption=" + avgFuelConsumption +
                '}';
    }
}
