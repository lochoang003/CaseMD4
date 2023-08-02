package com.casemodul4.repository;

import com.casemodul4.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepo extends CrudRepository<Post,Integer> {
}
