package com.casemodul4.service;

import com.casemodul4.model.Post;
import com.casemodul4.repository.IPostRepo;
import com.casemodul4.service.impl.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements ICrudService<Post> {
    @Autowired
    IPostRepo iPostRepo;
    @Override
    public List<Post> getAll() {
        return (List<Post>) iPostRepo.findAll();
    }

    @Override
    public void save(Post post) {
        iPostRepo.save(post);

    }



    @Override
    public void delete(int id) {
         iPostRepo.deleteById(id);
    }
}
