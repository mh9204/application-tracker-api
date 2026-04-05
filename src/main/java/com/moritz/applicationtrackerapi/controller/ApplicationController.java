package com.moritz.applicationtrackerapi.controller;

import com.moritz.applicationtrackerapi.dto.ApplicationResponse;
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
    public ApplicationResponse createApplication(@Valid @RequestBody CreateApplicationRequest request) {
        Application application = new Application();
        application.setCompanyName(request.getCompanyName());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());
        application.setApplicationDate(LocalDate.now());

        Application createdApplication = applicationService.createApplication(application);
        return mapToResponse(createdApplication);
    }

    @GetMapping
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        return mapToResponse(application);
    }

    @PatchMapping("/{id}/status")
    public ApplicationResponse updateStatus(@PathVariable Long id, @RequestBody ApplicationStatus newStatus) {
        Application updatedApplication = applicationService.updateStatus(id, newStatus);
        return mapToResponse(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

    private ApplicationResponse mapToResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setCompanyName(application.getCompanyName());
        response.setPosition(application.getPosition());
        response.setStatus(application.getStatus());
        response.setApplicationDate(application.getApplicationDate());
        response.setNotes(application.getNotes());

        return response;
    }
}