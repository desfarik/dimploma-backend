package com.medical_area.private_area.common.model;

import com.medical_area.public_area.models.User;
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
@Table(name = "user_relations", schema = "public")
public class UserPatients {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "patient_id")
    private Integer patientId;
}
