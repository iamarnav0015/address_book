package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;

import java.util.List;

public interface IAddressBookService {
    AddressBook addContact(AddressBookDTO addressBookDTO);
    AddressBook updateContact(int id, AddressBookDTO addressBookDTO);
    AddressBook getContactById(int id);
    List<AddressBook> getAllContacts();
    void deleteContact(int id);
}