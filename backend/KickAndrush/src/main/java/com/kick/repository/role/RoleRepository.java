package com.kick.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kick.entity.ERole;
import com.kick.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(ERole user);
}
