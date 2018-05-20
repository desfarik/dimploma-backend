package com.medical_area.public_area.repositories;

import com.medical_area.public_area.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByUserName(String name);

    User findFirstByPhone(String phone);

    User findFirstByPhoneOrUserName(String phone, String userName);

    boolean existsUserByUserName(String name);

    boolean existsUserByPhone(String phone);
}
