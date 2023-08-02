package com.casemodul4.repository;

import com.casemodul4.model.UserAcc;
import org.springframework.data.repository.CrudRepository;

public interface IUserAccRepo extends CrudRepository<UserAcc,Integer> {
     UserAcc findAllByUsernameAndEmail(String username,String email);
}
