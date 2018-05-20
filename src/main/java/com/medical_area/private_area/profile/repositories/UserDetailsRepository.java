package com.medical_area.private_area.profile.repositories;

import com.medical_area.private_area.profile.models.UserDetailsRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDetailsRepository extends CrudRepository<UserDetailsRequest, Integer> {
    UserDetailsRequest findFirstById(Integer userId);

    List<UserDetailsRequest> findAllByIdIn(List<Integer> ids);
}
