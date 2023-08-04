package com.casemodul4.service;

import com.casemodul4.model.UserAcc;
import com.casemodul4.model.dto.UserAccDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserAccService extends UserDetailsService {
    Iterable<UserAcc> findAll();

    UserAccDTO findById(int id);

    UserAcc save(UserAcc userAcc);

    void remove(int id);
    UserAcc getUserAccLogin1( String email, String password);
    UserAcc getUserAccLogin2(String username, String password);

//    UserDetails loadUserByUsername(String username);
}
