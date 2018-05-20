package com.medical_area.private_area.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient_history", schema = "public")
public class PatientHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "data")
    private String data;

    public PatientHistory(Integer patientId, String data) {
        this.patientId = patientId;
        this.data = data;
    }

    public PatientHistory(Integer doctorId, Integer patientId, String data) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.data = data;
    }
}
