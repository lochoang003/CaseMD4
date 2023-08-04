package com.casemodul4.model;

import com.casemodul4.model.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserAcc userAcc;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String img;
    private String video;
    private Date createDate;
    private int likeCount;
    private int commentCount;
    private int shareCount;
    public PostDTO postDTO() {
        return new PostDTO(id,content,img,video, userAcc.getId());
    }

}
