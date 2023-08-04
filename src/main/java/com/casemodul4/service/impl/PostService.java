package com.casemodul4.service.impl;

import com.casemodul4.model.Post;
import com.casemodul4.repository.IPostRepo;
import com.casemodul4.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements ICrudService<Post> {
    @Autowired
    IPostRepo iPostRepo;
    @Override
    public List<Post> getAll() {
        return (List<Post>) iPostRepo.findAll();
    }



    @Override
    public Post save(Post post) {
       return iPostRepo.save(post);
    }
    public List<Post> getAllPostByUserAcc(int id) {
        return iPostRepo.findAllPostByUserAccId(id);
    }


    @Override
    public Optional<Post> findById(int id) {
        return iPostRepo.findById(id);
    }


    @Override
    public void delete(int id) {
         iPostRepo.deleteById(id);
    }

    public void delete(Post post) {
         iPostRepo.delete(post);
    }
}
