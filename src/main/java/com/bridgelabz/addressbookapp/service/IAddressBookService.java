package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import java.util.List;

public interface IAddressBookService {
    AddressBook addContact(AddressBookDTO addressBookDTO);
    List<AddressBook> getAllContacts();
    AddressBook getContactById(int id);
    AddressBook updateContact(int id, AddressBookDTO addressBookDTO);
    void deleteContact(int id);
}
