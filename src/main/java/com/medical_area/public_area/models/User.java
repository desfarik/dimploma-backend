package com.medical_area.public_area.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty(value = "username")
    @Column(name = "email")
    private String userName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private UserRole role;

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public User(String userName, String password, String phone, String countryCode, UserRole userRole) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.countryCode = countryCode;
        this.role = userRole;
    }
}
