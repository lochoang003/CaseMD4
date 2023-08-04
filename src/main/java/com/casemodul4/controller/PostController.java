package com.casemodul4.controller;

import com.casemodul4.model.Post;
<<<<<<< HEAD
import com.casemodul4.model.UserAcc;
import com.casemodul4.model.dto.PostDTO;
import com.casemodul4.service.impl.PostService;
import com.casemodul4.service.impl.UserAccService;
=======
import com.casemodul4.service.PostService;

>>>>>>> bc267a5908768f9a643604bdebf038d026486da5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.Map;
>>>>>>> bc267a5908768f9a643604bdebf038d026486da5

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
<<<<<<< HEAD
    @Autowired
    UserAccService userAccService;
=======



>>>>>>> bc267a5908768f9a643604bdebf038d026486da5
    @GetMapping
    public List<Post> getAll() {


        return postService.getAll();
    }

    @GetMapping("/user/{idUserAcc}")
    public List<Post> getAllOfUserAcc(@PathVariable int idUserAcc) {
        return postService.getAllPostByUserAcc(idUserAcc);
    }

    @PostMapping("/createPost")
    public void create(@RequestBody Post post)  {

        postService.save(post);


    }
<<<<<<< HEAD
    @GetMapping("/{id}")
    public ResponseEntity<Post> findUserAccById(@PathVariable int id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
=======

    @PostMapping("/editPost")
    public void edit(@RequestBody Post post){
        System.out.println(post);
        postService.save(post);
>>>>>>> bc267a5908768f9a643604bdebf038d026486da5
    }

    @PostMapping("/edit/{idPost}")
    public ResponseEntity<Post> updatePost(@PathVariable int idPost, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(idPost);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(postOptional.get().getId());
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @GetMapping("/deletePost/{idPost}")
    public ResponseEntity<Post> remove(@PathVariable int idPost) {
        Optional<Post> postOptional = postService.findById(idPost);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.delete(idPost);
        return ResponseEntity.ok().build();
    }





//    @PostMapping("/editPost")
//    public void edit(@RequestBody Post post){
//        postService.save(post);
//    }

    @PostMapping("/deletePost")
    public void delete(@RequestBody Post post) {
        postService.delete(post.getId());
    }



}

