package com.casemodul4.controller;

import com.casemodul4.model.Post;
import com.casemodul4.model.Role;
import com.casemodul4.model.UserAcc;
import com.casemodul4.repository.IUserAccRepo;
import com.casemodul4.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserAccController {
    @Autowired
    IUserAccRepo iUserAccRepo;
    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/forgotPassword")
    public ResponseEntity<UserAcc> forgot(@RequestBody UserAcc userAcc) {
      UserAcc userAcc1=  iUserAccRepo.findAllByUsernameAndEmail(userAcc.getUsername(),userAcc.getEmail());
      if (userAcc1 !=null)
          return new ResponseEntity<>(userAcc1, HttpStatus.OK);
      else
          return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @PostMapping("/register")
    public ResponseEntity<UserAcc> createNewAcc(@RequestBody UserAcc userAcc){
        userAcc.setAvatar("https://cdn.pixabay.com/photo/2014/03/24/13/49/avatar-294480_960_720.png");
        userAcc.setCoverPhoto("https://file.vfo.vn/hinh/2014/3/anh-bia-facebook-7.jpg");
        userAcc.setDescription("");
        Role role = roleRepository.findByName("ROLE_USER");
        userAcc.setRole(role);
        iUserAccRepo.save(userAcc);
        return new ResponseEntity<>(userAcc, HttpStatus.OK);
    }

}
