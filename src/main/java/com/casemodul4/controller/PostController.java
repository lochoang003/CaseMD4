package com.casemodul4.controller;

import com.casemodul4.model.Post;
import com.casemodul4.service.PostService;
import com.casemodul4.service.StorageService;
import com.casemodul4.service.impl.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    StorageService storageService;


    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping("/createPost")
    public void create(@RequestBody Post post)  {
//        System.out.println(1);
//        storageService.uploadFile(file);

        postService.save(post);

//        @ExceptionHandler(StorageException.class)

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
