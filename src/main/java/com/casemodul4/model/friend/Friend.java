package com.casemodul4.model.friend;

import com.casemodul4.model.UserAcc;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserAcc userAcc;
    @ManyToOne
    private UserAcc friend;
    private byte status;
    //0: pending, 1: accepted, 2: declined
}
