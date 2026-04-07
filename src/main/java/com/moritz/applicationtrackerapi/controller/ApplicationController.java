package com.moritz.applicationtrackerapi.controller;

import com.moritz.applicationtrackerapi.dto.ApplicationResponse;
import com.moritz.applicationtrackerapi.dto.CreateApplicationRequest;
import com.moritz.applicationtrackerapi.dto.UpdateStatusRequest;
import com.moritz.applicationtrackerapi.model.Application;
import com.moritz.applicationtrackerapi.model.ApplicationStatus;
import com.moritz.applicationtrackerapi.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService; // Controller bekommt den Service nur einmal, diese Abhängigkeit wird über den Konstruktor injiziert und soll sich nicht mehr verändern

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(@Valid @RequestBody CreateApplicationRequest request) {
        Application application = new Application();
        application.setCompanyName(request.getCompanyName());
        application.setPosition(request.getPosition());
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());
        application.setApplicationDate(LocalDate.now());

        Application createdApplication = applicationService.createApplication(application);
        ApplicationResponse response = mapToResponse(createdApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
    public ApplicationResponse updateStatus(@PathVariable Long id,@Valid @RequestBody UpdateStatusRequest request) {
        Application updatedApplication = applicationService.updateStatus(id, request.getStatus());
        return mapToResponse(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
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