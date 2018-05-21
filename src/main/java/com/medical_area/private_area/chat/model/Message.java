package com.medical_area.private_area.chat.model;

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
@Table(name = "dialogs", schema = "public")
public class Message {
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
    @Column(name = "seen")
    private boolean seen;
    @Column(name = "is_doctor")
    private boolean doctor;

    public Message(Integer doctorId, Integer patientId, String data, boolean seen, boolean doctor) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.data = data;
        this.seen = seen;
        this.doctor = doctor;
    }
}
