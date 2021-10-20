package com.naumen.films.application.repository;

import com.naumen.films.application.model.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(String username);
}
