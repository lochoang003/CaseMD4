package com.casemodul4.controller.useracc;

import com.casemodul4.model.Role;
import com.casemodul4.model.UserAcc;
import com.casemodul4.repository.RoleRepository;
import com.casemodul4.service.useracc.IUserAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserAccController {
    @Autowired
    IUserAccService iUserAccService;
    @Autowired
    RoleRepository roleRepository;
    @PostMapping
    public ResponseEntity<UserAcc> createNewAcc(@RequestBody UserAcc userAcc){
        userAcc.setAvatar("https://cdn.pixabay.com/photo/2014/03/24/13/49/avatar-294480_960_720.png");
        userAcc.setCoverPhoto("https://file.vfo.vn/hinh/2014/3/anh-bia-facebook-7.jpg");
        userAcc.setDescription("");
        Role role = roleRepository.findByName("ROLE_USER");
        userAcc.setRole(role);
        iUserAccService.save(userAcc);
        return new ResponseEntity<>(userAcc, HttpStatus.OK);
    }


}
