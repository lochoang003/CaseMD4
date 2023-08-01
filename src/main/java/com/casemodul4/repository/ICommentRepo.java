package com.casemodul4.repository;

import com.casemodul4.model.Comment;
import com.casemodul4.model.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByUserAccIdAndPostId(int userId, int postId);
}
