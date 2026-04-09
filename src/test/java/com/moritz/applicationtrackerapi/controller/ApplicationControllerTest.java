package com.moritz.applicationtrackerapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moritz.applicationtrackerapi.dto.CreateApplicationRequest;
import com.moritz.applicationtrackerapi.model.ApplicationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //starte kompletten Spring Boot Kontext für den Test
@AutoConfigureMockMvc //MockMvc wird automatisch bereit gestellt
@ActiveProfiles("test") //aktiviere das Spring Profil "test" und lade die passende Konfigurationsdatei (das Muster dabei ist application.properties = allgemein, und bei application-test.properties konfiguration für profil test
class ApplicationControllerTest { // Integrationstest für Web Layer, keine reiner Unit Test

    @Autowired //man muss die Objekte nicht mehr mit new bauen
    private MockMvc mockMvc; //simuliert HTTP Requests an den Spring Controller ohne dass ein echter Browser oder HTTP-Client nötig ist

    @Autowired
    private ObjectMapper objectMapper; //Java Objekte in JSON umwandeln und umgekehrt

    @Test
    void createApplication_shouldReturnCreated() throws Exception { // wenn eine Application erstellt wird, soll created zurückkommen
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setCompanyName("SAP");
        request.setPosition("Werkstudent Java");
        request.setStatus(ApplicationStatus.APPLIED);
        request.setNotes("Erste Bewerbung");

        mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createApplication_withInvalidData_shouldReturnBadRequest() throws Exception {
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setCompanyName("");
        request.setPosition("");
        request.setStatus(null);

        mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getApplicationById_withNonExistingId_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/applications/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllApplications_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/applications"))
                .andExpect(status().isOk());
    }

    @Test
    void getApplicationsByStatus_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/applications")
                        .param("status", "APPLIED"))
                .andExpect(status().isOk());
    }

    @Test
    void updateStatus_shouldReturnOk() throws Exception { //throws Exception spart try catch da es so einfach weiter nach oben propagiert wird
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setCompanyName("SAP");
        request.setPosition("Werkstudent Java");
        request.setStatus(ApplicationStatus.APPLIED);
        request.setNotes("Erste Bewerbung");

        String responseBody = mockMvc.perform(post("/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long createdId = objectMapper.readTree(responseBody).get("id").asLong();

        mockMvc.perform(patch("/applications/" + createdId + "/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "status": "INTERVIEW"
                            }
                            """))
                .andExpect(status().isOk());
    }
}