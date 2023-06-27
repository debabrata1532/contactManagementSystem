package com.contactManagement.Contact.jpa;

import com.contactManagement.Contact.model.contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface contactJPA extends JpaRepository<contact, Integer> {


    @Query(value = "select contact from contact contact where lower(contact.email) like lower(concat('%', ?1,'%')) ")
    public List<contact> getListByEmail(String email);

    @Query(value = "select contact from contact contact where lower(contact.firstname) like lower(concat('%', ?1,'%'))  ")
    public List<contact> getListByFirstname(String firstname);
    @Query(value = "select contact from contact contact where lower(contact.lastname) like lower(concat('%', ?1,'%')) ")
    public List<contact> getListByLastname(String lastname);
    @Query(value = "select contact from contact contact where contact.mobile=?1")
    public List<contact> getListByMobile(String mobile);
}
