package com.casemodul4.repository;

import com.casemodul4.model.Post;
import com.casemodul4.model.UserAcc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPostRepo extends CrudRepository<Post,Integer> {
    Optional<Post> findById(int id);
    @Query(nativeQuery = true ,value = "select * from post where user_acc_id= :id")
    List<Post> findAllPostByUserAccId(@Param("id") int id);
}
