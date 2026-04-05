package com.moritz.applicationtrackerapi.dto;

import com.moritz.applicationtrackerapi.model.ApplicationStatus;

import java.time.LocalDate;

public class ApplicationResponse {

    private Long id;
    private String companyName;
    private String position;
    private ApplicationStatus status;
    private LocalDate applicationDate;
    private String notes;

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
