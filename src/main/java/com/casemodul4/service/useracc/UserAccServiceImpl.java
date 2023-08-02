package com.casemodul4.service.useracc;

import com.casemodul4.model.UserAcc;
import com.casemodul4.repository.UserAccRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccServiceImpl implements IUserAccService<UserAcc> {
    @Autowired
    UserAccRepository userAccRepository;

    @Override
    public List<UserAcc> getAllAcc() {
        return userAccRepository.findAll();
    }

    @Override
    public UserAcc findById(int id) {
        return userAccRepository.findById(id).get();
    }

    @Override
    public void save(UserAcc userAcc) {
        userAccRepository.save(userAcc);
    }

    @Override
    public void deleteById(int id) {
        userAccRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAcc userAcc = userAccRepository.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) userAcc.getRole());
        return new User(userAcc.getUsername(), userAcc.getPassword(), roles);
    }
}