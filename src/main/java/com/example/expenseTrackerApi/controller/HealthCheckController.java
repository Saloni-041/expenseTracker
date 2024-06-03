package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.entity.VersionModel;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public ResponseEntity<VersionModel> version() {
        VersionModel versionModel = new VersionModel();
        versionModel.setVersion("1.0");
        return new ResponseEntity<VersionModel>(versionModel, HttpStatus.OK);
    }
}
