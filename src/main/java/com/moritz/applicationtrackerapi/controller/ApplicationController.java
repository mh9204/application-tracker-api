package com.moritz.applicationtrackerapi.controller;

import com.moritz.applicationtrackerapi.model.Application;
import com.moritz.applicationtrackerapi.model.ApplicationStatus;
import com.moritz.applicationtrackerapi.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
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