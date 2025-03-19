package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.exception.UserException;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressBookService implements IAddressBookService {


    @Autowired
    private AddressBookRepository repository;

    @Override
    public List<AddressBookDTO> getAllContacts() {
        try {
            log.info("Fetching all contacts from the database.");
            return repository.findAll().stream()
                    .map(contact -> new AddressBookDTO(contact.getId(), contact.getName(), contact.getPhone()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching contacts: {}", e.getMessage());
            throw new UserException("Failed to fetch contacts. Please try again.");
        }
    }

    @Override
    public AddressBookDTO addContact(AddressBookDTO dto) {
        try {
            log.info("Saving new contact: {}", dto);
            AddressBook contact = new AddressBook();
            contact.setName(dto.getName());
            contact.setPhone(dto.getPhone());
            AddressBook savedContact = repository.save(contact);
            log.info("Contact saved successfully with ID: {}", savedContact.getId());
            return new AddressBookDTO(savedContact.getId(), savedContact.getName(), savedContact.getPhone());
        } catch (Exception e) {
            log.error("Error saving contact: {}", e.getMessage());
            throw new UserException("Failed to save contact. Please try again.");
        }
    }

    @Override
    public AddressBookDTO getContactById(Long id) {
        try {
            log.info("Fetching contact with ID: {}", id);
            Optional<AddressBook> contact = repository.findById(Math.toIntExact(id));
            if (contact.isEmpty()) {
                log.warn("Contact with ID {} not found.", id);
                throw new UserException("Contact not found with ID: " + id);
            }
            return new AddressBookDTO(contact.get().getId(), contact.get().getName(), contact.get().getPhone());
        } catch (Exception e) {
            log.error("Error fetching contact by ID: {}", e.getMessage());
            throw new UserException("Failed to retrieve contact. Please try again.");
        }
    }

    @Override
    public AddressBookDTO updateContact(long id, AddressBookDTO dto) {
        try {
            log.info("Updating contact with ID: {}", id);
            Optional<AddressBook> existingContact = repository.findById((int) id);
            if (existingContact.isEmpty()) {
                log.warn("Attempted to update non-existing contact with ID: {}", id);
                throw new UserException("Contact not found with ID: " + id);
            }

            AddressBook contact = existingContact.get();
            contact.setName(dto.getName());
            contact.setPhone(dto.getPhone());
            AddressBook updatedContact = repository.save(contact);

            log.info("Contact updated successfully: {}", updatedContact);
            return new AddressBookDTO(updatedContact.getId(), updatedContact.getName(), updatedContact.getPhone());

        } catch (Exception e) {
            log.error("Error updating contact: {}", e.getMessage());
            throw new UserException("Failed to update contact. Please try again.");
        }

    }

    @Override
    public void deleteContact(Long id) {
        try {
            log.info("Deleting contact with ID: {}", id);
            if (repository.existsById(Math.toIntExact(id))) {
                repository.deleteById(Math.toIntExact(id));
                log.info("Contact with ID {} deleted successfully.", id);
            } else {
                log.warn("Attempted to delete non-existing contact with ID: {}", id);
                throw new UserException("Contact not found with ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error deleting contact: {}", e.getMessage());
            throw new UserException("Failed to delete contact. Please try again.");
        }
    }
}


