package com.contactManagement.Contact.controller;


import com.contactManagement.Contact.model.authBody;
import com.contactManagement.Contact.model.contact;
import com.contactManagement.Contact.service.authenticationService;
import com.contactManagement.Contact.service.contactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Contact Management", description = "Create, Read, Update & Delete operation")
public class controller {



    @Autowired
    contactService service;


    @GetMapping("/test")
    public String test(){
        return "i am working";
    }

    @PostMapping("/saveContact")
    public ResponseEntity<?> saveContact(@RequestBody contact contact){

        return service.saveContact(contact);
    }

    @PutMapping("/updateContact")
    public ResponseEntity<?> updateContact(@RequestBody contact contact){

        return service.updateContact(contact);
    }
    @GetMapping("/getContact")
    public List<contact> getContactList(){

        return service.getContactList();
    }


    @GetMapping("/getContactByEmail")
    public List<contact> getContactList(@RequestParam String email){

        return service.getContactList(email);
    }
    @GetMapping("/getContactByFirstname")
    public List<contact> getContactListByFirstname(@RequestParam String firstname){

        return service.getContactListByFirstname(firstname);
    }
    @GetMapping("/getContactByLastname")
    public List<contact> getContactListByLastname(@RequestParam String lastname){

        return service.getContactListByLastname(lastname);
    }
    @GetMapping("/getContactByMobile")
    public List<contact> getContactListBtMobile(@RequestParam String mobile){

        return service.getContactListByMobile(mobile);
    }

    @DeleteMapping("/deleteContact")
    public ResponseEntity<?> getContactListBtMobile(@RequestParam int id){

        return service.removeContact(id);
    }



}
