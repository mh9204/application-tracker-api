package com.moritz.applicationtrackerapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id=1, id=2 usw.
    private Long id;

    private String companyName;

    private String position;

    @Enumerated(EnumType.STRING) //speichere ApplicationStatus in DB als String
    private ApplicationStatus status;

    private LocalDate applicationDate;

    private String notes;

    public Application() { //fuer JPA/ Hibernate, um aus der DB Objekte zu erzeugen
    }

    public Application(String companyName, String position, ApplicationStatus status, LocalDate applicationDate, String notes) {
        this.companyName = companyName;
        this.position = position;
        this.status = status;
        this.applicationDate = applicationDate;
        this.notes = notes;
    } //Konstruktor ohne id, da man die bei Objekterstellung noch nicht kennt

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}