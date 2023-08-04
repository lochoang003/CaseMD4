package com.casemodul4.controller.friend;

import com.casemodul4.model.Friend;
import com.casemodul4.model.UserAcc;

import com.casemodul4.model.dto.friendDTO.DeleteFriendRequestDTO;
import com.casemodul4.model.dto.friendDTO.FriendInfoDTO;
import com.casemodul4.model.dto.friendDTO.FriendRequestDTO;
import com.casemodul4.repository.IUserAccRepo;
import com.casemodul4.repository.friend.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@RestController

@RequestMapping("/api")
public class FriendController {
    @Autowired
    private IUserAccRepo userAccRepo;

    @Autowired
    private FriendRepository friendRepository;
//api gửi lời mời kết bạn
    @PostMapping("/sendAddFriend")
    public ResponseEntity<String> friendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        UserAcc sender = userAccRepo.findById( friendRequestDTO.getSenderId()).orElse(null);
        UserAcc receiver = userAccRepo.findById( friendRequestDTO.getReceiverId()).orElse(null);
        if (sender == null || receiver == null) {
            return ResponseEntity.notFound().build();
        }
        Friend existingFriendship = friendRepository.findByUserAccAndFriend(sender, receiver);
        if (existingFriendship != null && existingFriendship.getStatus() == 0) {
            return ResponseEntity.ok().body("Đã gửi lời kết bạn trước đó.");
        }
        Friend friendship = new Friend();
        friendship.setUserAcc(sender);
        friendship.setFriend(receiver);
        friendship.setStatus((byte) 0); // Mặc định là pending khi gửi yêu cầu kết bạn
        friendRepository.save(friendship);
        return ResponseEntity.ok().body("Đã gửi lời kết bạn thành công.");
    }
//api đồng ý kết bạn
    @PostMapping("/acceptFriendRequest")
    public ResponseEntity<String> acceptFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        UserAcc sender = userAccRepo.findById(friendRequestDTO.getSenderId()).orElse(null);
        UserAcc receiver = userAccRepo.findById(friendRequestDTO.getReceiverId()).orElse(null);
        if (sender == null || receiver == null) {
            return ResponseEntity.notFound().build();
        }
        Friend friendship = friendRepository.findByUserAccAndFriend(sender, receiver);
        if (friendship == null || friendship.getStatus() != 0) {
            return ResponseEntity.badRequest().body("Không tìm thấy yêu cầu kết bạn chờ xử lý.");
        }
        friendship.setStatus((byte) 1); // Cập nhật trạng thái từ "pending" sang "accepted"
        friendRepository.save(friendship);
        return ResponseEntity.ok("Đồng ý kết bạn thành công.");
    }
    //api lấy danh sách các bạn bè của 1 tài khoản
    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<FriendInfoDTO>> getFriends(@PathVariable("userId") Integer userId) {
        List<FriendInfoDTO> friendInfoList = new ArrayList<>();

        // Tìm tài khoản của người dùng dựa trên userId
        UserAcc user = userAccRepo.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Lấy danh sách bạn bè của người dùng
        List<Friend> friends = friendRepository.findAllByUserAccOrFriend(user, user);

        // Duyệt qua danh sách bạn bè và lấy thông tin cần trả về
        for (Friend friend : friends) {
            UserAcc friendUser = friend.getUserAcc().equals(user) ? friend.getFriend() : friend.getUserAcc();

            FriendInfoDTO friendInfo = new FriendInfoDTO();
            friendInfo.setAvatar(friendUser.getAvatar());
            friendInfo.setFullName(friendUser.getFullName());
            friendInfo.setDescription(friendUser.getDescription());

            friendInfoList.add(friendInfo);
        }
        return ResponseEntity.ok(friendInfoList);
    }
//api lấy danh sách các tài khoản có trong hệ thống
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserAcc>> getAllUsers() {
        List<UserAcc> allUsers = userAccRepo.findAll();
        return ResponseEntity.ok(allUsers);
    }
    //api kiểm tra 1 tài khoản trong danh sách các tài khoản đang có
    // trong hệ thống đã là bạn bè với tài khoản đang được đăng nhập hay chưa
    @GetMapping("/checkFriendship")
    public ResponseEntity<String> checkFriendship(@RequestParam("userId1") Integer userId1, @RequestParam("userId2") Integer userId2) {
        UserAcc user1 = userAccRepo.findById(userId1).orElse(null);
        UserAcc user2 = userAccRepo.findById(userId2).orElse(null);
        if (user1 == null || user2 == null) {
            return ResponseEntity.notFound().build();
        }
        Friend friendship = friendRepository.findFriendship(user1, user2);
        if (friendship != null && friendship.getStatus() == 1) {
            return ResponseEntity.ok("Hai tài khoản là bạn bè.");
        } else {
            return ResponseEntity.ok("Hai tài khoản không là bạn bè.");
        }
    }
    //api huy ket bạn

    @PostMapping("/unfriend")
    public ResponseEntity<String> unfriend(@RequestBody FriendRequestDTO friendRequestDTO) {
        UserAcc user1 = userAccRepo.findById(friendRequestDTO.getSenderId()).orElse(null);
        UserAcc user2 = userAccRepo.findById(friendRequestDTO.getReceiverId()).orElse(null);
        if (user1 == null || user2 == null) {
            return ResponseEntity.notFound().build();
        }
        Friend friendship = friendRepository.findFriendship(user1, user2);
        if (friendship != null && friendship.getStatus() == 1) {
            // Xóa mối quan hệ bạn bè
            friendRepository.delete(friendship);
            return ResponseEntity.ok("Hủy kết bạn thành công.");
        } else {
            return ResponseEntity.badRequest().body("Hai tài khoản không là bạn bè.");
        }
    }
    //api trả về các lời mời kết bạn mà tài khoản đang đăng nhập nhận được
    @GetMapping("/friendRequests")
    public ResponseEntity<List<Friend>> getFriendRequestsOfLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUsername = userDetails.getUsername();
        UserAcc loggedInUser = userAccRepo.findByUsername(loggedInUsername);
        System.out.println(loggedInUsername);
        // Lấy danh sách các lời mời kết bạn có trạng thái "pending" và có user là người nhận là tài khoản đang đăng nhập
        List<Friend> friendRequests = friendRepository.findAllByFriendAndStatus(loggedInUser, (byte) 0);
        // Kiểm tra nếu danh sách rỗng, trả về ResponseEntity với danh sách lời mời trống
        if (friendRequests.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(friendRequests);
    }
//api xóa 1 lời mời kết bạn mà tài khoản đang đăng nhâp nhận được
@DeleteMapping("/friendRequests")
public ResponseEntity<String> deleteFriendRequest(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody DeleteFriendRequestDTO deleteRequestDTO) {
    String loggedInUsername = userDetails.getUsername();
    UserAcc loggedInUser = userAccRepo.findByUsername(loggedInUsername);
    // Kiểm tra xem lời mời kết bạn có tồn tại và có được gửi đến người dùng đang đăng nhập không
    Friend friendRequest = friendRepository.findById(deleteRequestDTO.getRequestId()).orElse(null);
    if (friendRequest == null || !friendRequest.getFriend().equals(loggedInUser)) {
        return ResponseEntity.notFound().build();
    }
    // Xóa lời mời kết bạn
    friendRepository.delete(friendRequest);
    return ResponseEntity.ok("Đã xóa lời mời kết bạn thành công.");
}
}
