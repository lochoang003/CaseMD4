package com.casemodul4.service.Impl;

import com.casemodul4.model.Comment;
import com.casemodul4.model.dto.CommentDto;
import com.casemodul4.repository.ICommentRepo;
import com.casemodul4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    ICommentRepo commentRepo;


    @Override
    public List<CommentDto> findByUserAccIdAndPostId(int idUser, int idPost) {

        List<Comment> comments = commentRepo.findByUserAccIdAndPostId(idUser, idPost);
        return comments.stream().map(Comment::toDto).collect(Collectors.toList());
    }
}
