package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@Validated
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
        Contact newContact = contactService.addContact(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @Valid @RequestBody Contact contactDetails) {
        Contact updatedContact = contactService.updateContact(id, contactDetails);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}

