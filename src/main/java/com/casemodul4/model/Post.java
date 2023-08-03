package com.casemodul4.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserAcc userAcc;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String ou;
    private String video;
    private Date createDate;
    private int likeCount;
    private int commentCount;
    private int shareCount;
}
