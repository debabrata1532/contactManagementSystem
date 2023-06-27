package com.contactManagement.Contact.service;

import com.contactManagement.Contact.model.authBody;
import org.springframework.http.ResponseEntity;

public interface authenticationService {


public ResponseEntity<?> authenticate (authBody requestBody) throws Exception;

}
