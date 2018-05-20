package com.medical_area.private_area.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient_progress_status", schema = "public")
public class PatientProgressStatus {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "data")
    private String data;

    public PatientProgressStatus(Integer patientId, String data) {
        this.patientId = patientId;
        this.data = data;
    }
}
