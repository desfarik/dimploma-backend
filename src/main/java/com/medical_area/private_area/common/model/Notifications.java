package com.medical_area.private_area.common.model;

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
@Table(name = "notifications", schema = "public")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "identity")
    private String identity;
    @Column(name = "user_id")
    private Integer userId;

    public Notifications(String identity) {
        this.identity = identity;
    }

    public Notifications(String identity, Integer userId) {
        this.identity = identity;
        this.userId = userId;
    }
}
