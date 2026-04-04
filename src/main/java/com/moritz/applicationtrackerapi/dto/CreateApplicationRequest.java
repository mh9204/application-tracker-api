package com.moritz.applicationtrackerapi.dto;

import com.moritz.applicationtrackerapi.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateApplicationRequest {

    @NotBlank(message = "must not be blank")
    private String companyName;

    @NotBlank(message = "must not be blank")
    private String position;

    @NotNull(message = "must not be null")
    private ApplicationStatus status;
    private String notes;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}