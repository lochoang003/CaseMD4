package com.casemodul4.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String avatar;
    private String coverPhoto;
    private String Description;
    @ManyToOne
    private Role role;

}
