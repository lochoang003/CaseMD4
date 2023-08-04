package com.casemodul4.service.impl;

import java.util.List;

public interface ICrudService<E> {
    List<E> getAll();
    void save(E e);

    void  delete(int id);

}
