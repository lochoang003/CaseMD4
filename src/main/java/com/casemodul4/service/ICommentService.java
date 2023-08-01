package com.casemodul4.service;

import com.casemodul4.model.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    List<CommentDto> findByUserAccIdAndPostId(int idUser, int idPost);

}
