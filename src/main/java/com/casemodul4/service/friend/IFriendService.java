package com.casemodul4.service.friend;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface IFriendService<E>  {
    Page<E> getAllListFriend();
    E findById(int id);
    void save(E e);
    void deleteById(int id);
}
