package com.bridgelabz.addressbookapp.service;



import com.bridgelabz.addressbookapp.dto.AddressBookDTO;

import java.util.List;

public interface IAddressBookService {
    List<AddressBookDTO> getAllContacts();
    AddressBookDTO addContact(AddressBookDTO dto);


    AddressBookDTO getContactById(Long id);

    AddressBookDTO updateContact(long id, AddressBookDTO dto);

    void deleteContact(Long id);
}