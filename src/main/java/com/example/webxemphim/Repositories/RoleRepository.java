package com.example.webxemphim.Repositories;

import com.example.webxemphim.models.ERole;
import com.example.webxemphim.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
