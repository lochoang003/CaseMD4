package com.casemodul4.controller;

import com.casemodul4.model.Post;
import com.casemodul4.model.UserAcc;
import com.casemodul4.model.dto.UserAccDTO;
import com.casemodul4.repository.IUserAccRepo;
import com.casemodul4.service.impl.UserAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserAccController {
    @Autowired
    IUserAccRepo iUserAccRepo;
    @Autowired
    UserAccService userAccService;

    @PostMapping("/forgotPassword")
    public ResponseEntity<UserAcc> forgot(@RequestBody UserAcc userAcc) {
      UserAcc userAcc1=  iUserAccRepo.findAllByUsernameAndEmail(userAcc.getUsername(),userAcc.getEmail());
      if (userAcc1 !=null)
          return new ResponseEntity<>(userAcc1, HttpStatus.OK);
      else
          return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @PostMapping("/userAccDetail/{userAccId}")
    public ResponseEntity<UserAccDTO> findUserAccDTOById(@PathVariable int userAccId){
     UserAccDTO userAccDTO = userAccService.findById(userAccId);
        if (userAccDTO != null) {
            return new ResponseEntity<>(userAccDTO,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
