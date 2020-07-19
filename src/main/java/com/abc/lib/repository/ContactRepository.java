package com.abc.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.lib.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
