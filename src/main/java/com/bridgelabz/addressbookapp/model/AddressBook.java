package com.bridgelabz.addressbookapp.model;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "addressbook_db")
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    // Default Constructor (mandatory for Hibernate)
    public AddressBook() {}

    // Parameterized Constructor (for DTO mapping)
    public AddressBook(AddressBookDTO dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.phoneNumber = dto.getPhoneNumber();
        this.email = dto.getEmail();
    }

    // Getters and Setters (manual)

}