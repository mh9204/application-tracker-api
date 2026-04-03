package com.moritz.applicationtrackerapi.service;

import com.moritz.applicationtrackerapi.model.Application;
import com.moritz.applicationtrackerapi.model.ApplicationStatus;
import com.moritz.applicationtrackerapi.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application createApplication(Application application) { //von außen kommt application rein als JSON dann wird innen daraus Application application
        return applicationRepository.save(application); //rückgabe ist oft JSON Objekt mit id
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    } //orElseThrow erwartet Funktion die eine exception erzeugt

    public Application updateStatus(Long id, ApplicationStatus newStatus) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        application.setStatus(newStatus);
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("Application not found with id: " + id);
        }

        applicationRepository.deleteById(id); //deletion successfull message kommt im frontend
    }
}
