package com.casemodul4.service.useracc;

import com.casemodul4.model.UserAcc;
import com.casemodul4.repository.UserAccRepository;
import com.model.Role;
import com.repository.RoleRepo;
import com.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserAccServiceImpl implements IUserAccService<UserAcc> {
    @Autowired
    UserAccRepository userAccRepository;

    @Override
    public Page<UserAcc> getAllAcc() {
        return null;
    }

    @Override
    public UserAcc findById(int id) {
        return null;
    }

    @Override
    public void save(UserAcc userAcc) {

    }

    @Override
    public void deleteById(int id) {

    }
}