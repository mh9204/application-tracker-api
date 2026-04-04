package com.moritz.applicationtrackerapi.controller;

import com.moritz.applicationtrackerapi.dto.CreateApplicationRequest;
import com.moritz.applicationtrackerapi.model.Application;
import com.moritz.applicationtrackerapi.model.ApplicationStatus;
import com.moritz.applicationtrackerapi.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Application createApplication(@Valid @RequestBody CreateApplicationRequest request) {
        Application application = new Application();
        application.setCompanyName(request.getCompanyName());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());
        application.setApplicationDate(LocalDate.now());

        return applicationService.createApplication(application);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PatchMapping("/{id}/status") // kein PUT, da man nur einen Teil der Bewerbung ändert
    public Application updateStatus(@PathVariable Long id, @RequestBody ApplicationStatus newStatus) {
        return applicationService.updateStatus(id, newStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }
}