package com.interbank.demo.antifraudservice.controller;

import com.interbank.demo.antifraudservice.service.DatabaseTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseTestController {

    private final DatabaseTestService databaseTestService;

    @Autowired
    public DatabaseTestController(DatabaseTestService databaseTestService) {
        this.databaseTestService = databaseTestService;
    }

    @GetMapping("/test-db-connection")
    public String testDatabaseConnection() {
        return databaseTestService.testConnection();
    }
}
