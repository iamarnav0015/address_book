package com.bridgelabz.addressbookapp.model;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBook {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor using DTO
    public AddressBook(int id, AddressBookDTO addressBookDTO) {
        this.id = id;
        this.name = addressBookDTO.getName();
        this.address = addressBookDTO.getAddress();
        this.phoneNumber = addressBookDTO.getPhoneNumber();
        this.email = addressBookDTO.getEmail();
    }

    // Method to update contact details using DTO
    public void update(AddressBookDTO addressBookDTO) {
        this.name = addressBookDTO.getName();
        this.address = addressBookDTO.getAddress();
        this.phoneNumber = addressBookDTO.getPhoneNumber();
        this.email = addressBookDTO.getEmail();
    }

    public int getId() {
        return id;
    }

}
