package com.medical_area.private_area.patients.repositories;

import com.medical_area.private_area.patients.models.SendToMobile;
import org.springframework.data.repository.CrudRepository;

public interface SendToMobileRepository extends CrudRepository<SendToMobile, Integer> {

    SendToMobile findFirstByPatientId(Integer patientId);
}
