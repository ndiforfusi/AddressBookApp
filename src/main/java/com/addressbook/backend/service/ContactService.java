package com.addressbook.backend.service;

import com.addressbook.backend.exception.ContactNotFoundException;
import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Fetch all contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(String id) {
        return contactRepository.findById(id)
            .orElseThrow(() -> new ContactNotFoundException(id));
    }

    // Add a new contact
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Update an existing contact
    public Contact updateContact(String id, Contact contactDetails) {
        return contactRepository.findById(id)
            .map(contact -> {
                contact.setFirstName(contactDetails.getFirstName());
                contact.setLastName(contactDetails.getLastName());
                contact.setEmail(contactDetails.getEmail());
                contact.setDob(contactDetails.getDob());
                contact.setPhone(contactDetails.getPhone());
                return contactRepository.save(contact);
            })
            .orElseThrow(() -> new ContactNotFoundException(id));
    }

    // Delete a contact
    public void deleteContact(String id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        contactRepository.deleteById(id);
    }
}
