package com.casemodul4.repository;

import com.casemodul4.model.UserAcc;
import com.casemodul4.model.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
    Friend findByUserAccAndFriend(UserAcc userAcc, UserAcc friend);
}