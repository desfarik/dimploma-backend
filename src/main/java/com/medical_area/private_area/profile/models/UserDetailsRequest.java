package com.medical_area.private_area.profile.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "user_details", schema = "public")
public class UserDetailsRequest {
    @Id
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "details")
    private String details;

    public UserDetailsRequest(String details) {
        this.details = details;
    }
}