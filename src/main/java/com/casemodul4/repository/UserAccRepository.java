package com.casemodul4.repository;

import com.casemodul4.model.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAcc, Long> {
    UserAcc findByUsername(String username);
}