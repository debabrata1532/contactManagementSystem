package com.contactManagement.Contact.service;

import com.contactManagement.Contact.model.contact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface contactService {


    public ResponseEntity<?> saveContact(contact contact);
    public ResponseEntity<?> updateContact(contact contact);
    public List<contact> getContactList();
    public List<contact> getContactList(String email);
    public List<contact> getContactListByFirstname(String firstname);
    public List<contact> getContactListByLastname(String lastname);
    public List<contact> getContactListByMobile(String mobile);
    public ResponseEntity<?> removeContact(int id);
}
