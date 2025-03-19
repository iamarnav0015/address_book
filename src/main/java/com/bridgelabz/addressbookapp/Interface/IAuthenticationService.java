package com.bridgelabz.addressbookapp.Interface;


import com.bridgelabz.addressbookapp.dto.AuthUserDTO;
import com.bridgelabz.addressbookapp.model.AuthUser;
public interface IAuthenticationService {
    AuthUser register(AuthUserDTO userDTO) throws Exception;

}