package com.medical_area.private_area.common.repository;

import com.medical_area.private_area.common.model.UserPatients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPatientsRepository extends CrudRepository<UserPatients, Integer> {

    List<UserPatients> findAllByDoctorId(Integer id);

}
