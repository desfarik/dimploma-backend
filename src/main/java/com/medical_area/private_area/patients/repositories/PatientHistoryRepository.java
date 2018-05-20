package com.medical_area.private_area.patients.repositories;

import com.medical_area.private_area.patients.models.PatientHistory;
import org.springframework.data.repository.CrudRepository;

public interface PatientHistoryRepository extends CrudRepository<PatientHistory, Integer> {

    PatientHistory findFirstByPatientIdAndDoctorId(Integer patientId, Integer doctorId);
}
