package com.casemodul4.repository;

import com.casemodul4.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
Role findByName(String name);
}