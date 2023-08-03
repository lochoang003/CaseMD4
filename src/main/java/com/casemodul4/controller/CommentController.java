package com.casemodul4.controller;

import com.casemodul4.model.dto.CommentDto;
import com.casemodul4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;

    @GetMapping("/{userId}/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByUserAndPost(@PathVariable int userId, @PathVariable int postId) {
        List<CommentDto> comments = commentService.findByUserAccIdAndPostId(userId, postId);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/{idP}")
    public ResponseEntity<List<CommentDto>> getAllByPostId(@PathVariable int idP){
    List<CommentDto> commentDtos = commentService.getAllByPostId(idP);
        return ResponseEntity.ok(commentDtos);
    }
}
