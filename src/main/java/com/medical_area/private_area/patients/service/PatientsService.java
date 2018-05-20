package com.medical_area.private_area.patients.service;

import com.medical_area.private_area.common.service.UserService;
import com.medical_area.private_area.patients.models.PatientHistory;
import com.medical_area.private_area.patients.models.PatientProgressStatus;
import com.medical_area.private_area.patients.models.SendToMobile;
import com.medical_area.private_area.patients.repositories.PatientHistoryRepository;
import com.medical_area.private_area.patients.repositories.PatientProgressRepository;
import com.medical_area.private_area.patients.repositories.SendToMobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientsService {

    @Autowired
    private PatientHistoryRepository patientHistoryRepository;
    @Autowired
    private PatientProgressRepository patientProgressRepository;
    @Autowired
    private SendToMobileRepository sendToMobileRepository;
    @Autowired
    private UserService userService;

    public PatientHistory getPatientHistory(Integer patientId) {
        return patientHistoryRepository.findFirstByPatientIdAndDoctorId(patientId, userService.getCurrentUser().getId());
    }

    public void savePatientHistory(PatientHistory patientHistory) {
        PatientHistory newPatientHistory = Optional.ofNullable(getPatientHistory(patientHistory.getPatientId()))
                .orElse(new PatientHistory(userService.getCurrentUser().getId(), patientHistory.getPatientId(), patientHistory.getData()));
        newPatientHistory.setData(patientHistory.getData());
        patientHistoryRepository.save(newPatientHistory);
    }

    public PatientProgressStatus getPatientProgress(Integer patientId) {
        return patientProgressRepository.findFirstByPatientId(patientId);
    }

    public void savePatientProgress(PatientProgressStatus progressStatus) {
        patientProgressRepository.save(new PatientProgressStatus(progressStatus.getPatientId(), progressStatus.getData()));
    }

    public void saveSendToMobile(SendToMobile sendToMobile) {
        sendToMobileRepository.save(new SendToMobile(sendToMobile.getPatientId(), sendToMobile.getData(), false));
    }
}
