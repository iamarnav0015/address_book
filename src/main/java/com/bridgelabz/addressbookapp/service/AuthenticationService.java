package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AuthUserDTO;
import com.bridgelabz.addressbookapp.Interface.IAuthenticationService;
import com.bridgelabz.addressbookapp.repository.AuthenticationRepository;
import com.bridgelabz.addressbookapp.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    AuthenticationRepository authUserRepository;

    @Override
    public AuthUser register(AuthUserDTO userDTO) throws Exception {
        AuthUser user = new AuthUser(userDTO);

        authUserRepository.save(user);

        return user;
    }


}