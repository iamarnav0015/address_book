package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.addressbookapp.rabbitmq.AddressBookProducer;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @Autowired
    private AddressBookProducer addressBookProducer;

    @PostMapping("/add")
    public ResponseEntity<AddressBookDTO> addContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookDTO addedContact = addressBookService.addContact(addressBookDTO);
        String message = "New contact added: " + addedContact.getName() + " (" + addedContact.getEmail() + ")";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(addressBookService.addContact(addressBookDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDTO> getContactById(@PathVariable int id) {
        AddressBookDTO contact = addressBookService.getContactById((long) id);
        String message = "Contact retrieved: " + contact.getName() + " (" + contact.getEmail() + ")";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(contact);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressBookDTO>> getAllContacts() {
        List<AddressBookDTO> contacts = addressBookService.getAllContacts();
        String message = contacts.size() + " contacts retrieved.";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(contacts);    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBookDTO> updateContact(@PathVariable int id, @Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookDTO updatedContact = addressBookService.updateContact(id, addressBookDTO);
        String message = "Contact updated: " + updatedContact.getName() + " (" + updatedContact.getEmail() + ")";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(updatedContact);    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        addressBookService.deleteContact((long) id);
        String message = "Contact with ID " + id + " deleted.";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.noContent().build();
    }
}