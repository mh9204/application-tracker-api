package com.moritz.applicationtrackerapi.repository;

import com.moritz.applicationtrackerapi.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}