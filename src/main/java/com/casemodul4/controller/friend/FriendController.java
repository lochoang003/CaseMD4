package com.casemodul4.controller.friend;

import com.casemodul4.model.UserAcc;
import com.casemodul4.model.friend.Friend;
import com.casemodul4.model.friend.FriendRequestDTO;
import com.casemodul4.repository.FriendRepository;
import com.casemodul4.repository.UserAccRepository;
import com.casemodul4.service.friend.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FriendController {
    @Autowired
    private UserAccRepository userAccRepository;

    @Autowired
    private FriendRepository friendRepository;

    @PostMapping("/sendAddFriend")
    public ResponseEntity<String> friendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        UserAcc sender = userAccRepository.findById( friendRequestDTO.getSenderId()).orElse(null);
        UserAcc receiver = userAccRepository.findById( friendRequestDTO.getReceiverId()).orElse(null);
        if (sender == null || receiver == null) {
            return ResponseEntity.notFound().build();
        }
        Friend existingFriendship = friendRepository.findByUserAccAndFriend(sender, receiver);
        if (existingFriendship != null) {
            return ResponseEntity.badRequest().body("Đã kết bạn trước đó.");
        }
        Friend friendship = new Friend();
        friendship.setUserAcc(sender);
        friendship.setFriend(receiver);
        friendship.setStatus((byte) 0); // Mặc định là pending khi gửi yêu cầu kết bạn
        friendRepository.save(friendship);
        return ResponseEntity.ok("Gửi yêu cầu kết bạn thành công.");
    }

}
