package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.rabbitmq.AddressBookProducer;
import com.bridgelabz.addressbookapp.service.IAddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressService addressBookService;

    @Autowired
    private AddressBookProducer addressBookProducer;

    @PostMapping("/add")
    @Operation(summary = "Adds a new contact", description = "Adds a new contact")
    public ResponseEntity<AddressBook> addContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook addedContact = addressBookService.addContact(addressBookDTO);
        String message = "New contact added: " + addedContact.getName() + " (" + addedContact.getEmail() + ")";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(addedContact);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets contact by id", description = "Searches a contact by id")
    public ResponseEntity<AddressBook> getContactById(@PathVariable int id) {
        AddressBook contact = addressBookService.getContactById(id);
        String message = "Contact retrieved: " + contact.getName() + " (" + contact.getEmail() + ")";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(contact);
    }

    @GetMapping("/all")
    @Operation(summary = "Gets all contacts", description = "Gives all contacts")
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        List<AddressBook> contacts = addressBookService.getAllContacts();
        String message = contacts.size() + " contacts retrieved.";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a contact", description = "Update a contact")
    public ResponseEntity<AddressBook> updateContact(@PathVariable int id, @Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook updatedContact = addressBookService.updateContact(id, addressBookDTO);
        String message = "Contact updated: " + updatedContact.getName() + " (" + updatedContact.getEmail() + ")";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a contact", description = "Deletes a contact")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        addressBookService.deleteContact(id);
        String message = "Contact with ID " + id + " deleted.";
        addressBookProducer.sendMessage(message);
        return ResponseEntity.noContent().build();
    }
}