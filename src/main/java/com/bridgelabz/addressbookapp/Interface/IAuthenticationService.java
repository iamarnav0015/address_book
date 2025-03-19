package com.bridgelabz.addressbookapp.Interface;


import com.bridgelabz.addressbookapp.dto.AuthUserDTO;
import com.bridgelabz.addressbookapp.dto.LoginDTO;
import com.bridgelabz.addressbookapp.model.AuthUser;
public interface IAuthenticationService {
    AuthUser register(AuthUserDTO userDTO) throws Exception;
    String login(LoginDTO loginDTO);
    String forgotPassword(String email, String newPassword);
    String resetPassword(String email, String currentPassword, String newPassword);

}