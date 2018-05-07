package com.medical.area.public_area.repositories;

import com.medical.area.public_area.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    UserRole findUserRoleById(Integer id);
}
