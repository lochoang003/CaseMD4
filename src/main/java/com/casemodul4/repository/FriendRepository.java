package com.casemodul4.repository;

import com.casemodul4.model.friend.Friend;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FriendRepo extends PagingAndSortingRepository<Friend, Integer> {

}