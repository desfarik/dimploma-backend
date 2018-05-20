package com.medical_area.private_area.patients.repositories;

import com.medical_area.private_area.patients.models.PatientProgressStatus;
import org.springframework.data.repository.CrudRepository;

public interface PatientProgressRepository extends CrudRepository<PatientProgressStatus, Integer> {

    PatientProgressStatus findFirstByPatientId(Integer patient_id);
}
