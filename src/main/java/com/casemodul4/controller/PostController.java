package com.casemodul4.controller;

import com.casemodul4.model.Post;
import com.casemodul4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping("/createPost")
    public void create(@RequestBody Post post) {
        postService.save(post);
    }

    @PostMapping("/editPost")
    public void edit(@RequestBody Post post){
        postService.save(post);
    }

    @PostMapping("/deletePost")
    public void delete(@RequestBody Post post) {
        postService.delete(post.getId());
    }
}
