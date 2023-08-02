package com.casemodul4.repository;

import com.casemodul4.model.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccRepository extends JpaRepository<UserAcc, Integer> {
    UserAcc findByUsername(String username);
}