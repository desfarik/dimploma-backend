package com.medical_area.private_area.patients.controller;

import com.medical_area.private_area.patients.models.PatientHistory;
import com.medical_area.private_area.patients.models.PatientProgressStatus;
import com.medical_area.private_area.patients.models.SendToMobile;
import com.medical_area.private_area.patients.service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PatientRsService {

    @Autowired
    private PatientsService patientsService;

    @RequestMapping(value = "patients/history/get/{patientId}", method = RequestMethod.GET)
    public PatientHistory getPatientHistory(@PathVariable Integer patientId){
        return patientsService.getPatientHistory(patientId);
    }

    @RequestMapping(value = "patients/history/save", method = RequestMethod.POST)
    public ResponseEntity savePatientHistory(@RequestBody PatientHistory patientHistory) {
        patientsService.savePatientHistory(patientHistory);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "patients/progress/get/{patientId}", method = RequestMethod.GET)
    public PatientProgressStatus getPatientProgress(@PathVariable Integer patientId) {
        return patientsService.getPatientProgress(patientId);
    }

    //ALEXANDER
    @RequestMapping(value = "patients/progress/save", method = RequestMethod.POST)
    public ResponseEntity savePatientProgress(@Valid @RequestBody PatientProgressStatus progressStatus) {
        patientsService.savePatientProgress(progressStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "patients/mobile/send", method = RequestMethod.POST)
    public ResponseEntity setMobileInfo(@Valid @RequestBody SendToMobile sendToMobile) {
        patientsService.saveSendToMobile(sendToMobile);
        return new ResponseEntity(HttpStatus.OK);
    }
    //ALEXANDER
    @RequestMapping(value = "patients/mobile/get/{patientId}", method = RequestMethod.GET)
    public ResponseEntity getMobileInfo(@PathVariable Integer patientId) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
