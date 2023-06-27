package com.contactManagement.Contact.controller;


import com.contactManagement.Contact.model.authBody;
import com.contactManagement.Contact.service.authenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authenticate", description = "Get Authorize token by providing correct username and password")
public class authenticationController {
    @Autowired
    authenticationService authService;
    @PostMapping("/authentication")
    public ResponseEntity<?> authenticateUser(@RequestBody authBody requestBody) throws Exception {

        return authService.authenticate(requestBody);

    }
}
