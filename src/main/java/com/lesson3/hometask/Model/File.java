package com.lesson3.hometask.Model;

import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class File {

    @Id
    @SequenceGenerator(name = "FILE_SEQ", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ")
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FORMAT")
    private String format;
    @Column(name = "SIZE_FILE")
    private int sizeFile;
    @ManyToOne
    @JoinColumn(name = "STORAGE_ID", nullable = false )
    private Storage storage;

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public long getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(int sizeFile) {
        this.sizeFile = sizeFile;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", sizeFile=" + sizeFile +
                ", storage=" + storage +
                '}';
    }
}
