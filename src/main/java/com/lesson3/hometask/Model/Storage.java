package com.lesson3.hometask.Model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "STORAGE")
public class Storage {
    @Id
    @SequenceGenerator(name = "STORAGE_SEQ", sequenceName = "STORAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORAGE_SEQ")
    @Column(name = "ID")
    private int id;
    @Column(name = "FORMATS_SUPPORTED")
    private String formatsSupported;
    @Column(name = "STORAGE_COUNTRY")
    private String storageCountry;
    @Column(name = "STORAGE_MAX_SIZE")
    private Long storageMaxSize;

    public Storage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public long getStorageMaxSize() {
        return storageMaxSize;
    }

    public void setStorageMaxSize(Long storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported='" + formatsSupported + '\'' +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize=" + storageMaxSize +
                '}';
    }
}
