package com.casemodul4.service.useracc;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserAccService<E> extends UserDetailsService  {
    List<E> getAllAcc();
    E findById(int id);
    void save(E e);
    void deleteById(int id);
}
