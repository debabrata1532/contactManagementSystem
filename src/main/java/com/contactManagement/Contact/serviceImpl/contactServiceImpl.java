package com.contactManagement.Contact.serviceImpl;

import com.contactManagement.Contact.jpa.contactJPA;
import com.contactManagement.Contact.model.contact;
import com.contactManagement.Contact.service.contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class contactServiceImpl implements contactService {

    @Autowired
    contactJPA jpa;
    @Override
    public ResponseEntity<?> saveContact(contact contact) {


        List<contact> response = Collections.singletonList(jpa.save(contact));
       if(response.size() > 0){
           return ResponseEntity.ok("Contact has been uploaded");
       }else{
           return (ResponseEntity<?>) ResponseEntity.status(500);
       }
    }

    @Override
    public ResponseEntity<?> updateContact(contact contact) {


            List<contact> response = Collections.singletonList(jpa.save(contact));
            if(response.size() > 0){
                return ResponseEntity.ok("Contact has been updated");
            }else{
                return (ResponseEntity<?>) ResponseEntity.status(500);
            }

    }

    @Override
    public List<contact> getContactList() {
        return jpa.findAll();
    }

    @Override
    public List<contact> getContactList(String email) {


        return jpa.getListByEmail(email);
    }

    @Override
    public List<contact> getContactListByFirstname(String firstname) {
       return jpa.getListByFirstname(firstname);
    }

    @Override
    public List<contact> getContactListByLastname(String lastname) {
        return jpa.getListByLastname(lastname);
    }

    @Override
    public List<contact> getContactListByMobile(String mobile) {
        return jpa.getListByMobile(mobile);
    }

    @Override
    public ResponseEntity<?> removeContact(int id) {

        List<contact> response = jpa.findAllById(Collections.singleton(id));

        if(response.size()>0){
            jpa.deleteAllById(Collections.singleton(id));
            return ResponseEntity.ok("Contact has been deleted");
        }else{
            return ResponseEntity.ok("There is no contact with this id");
        }


    }
}
