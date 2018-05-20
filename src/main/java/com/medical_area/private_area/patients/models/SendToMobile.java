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
@Table(name = "send_to_mobile", schema = "public")
public class SendToMobile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "data")
    private String data;
    @Column(name = "send")
    private boolean send;

    public SendToMobile(Integer patientId, String data, boolean send) {
        this.patientId = patientId;
        this.data = data;
        this.send = send;
    }
}
