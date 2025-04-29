package com.jeeshantraders.jeeshanTraders.Controller;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeeshantraders.jeeshanTraders.Entities.User;
import com.jeeshantraders.jeeshanTraders.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> Get(){
        List<User> userList = userService.GetInfo();
        if ( userList != null){
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> GetById(@PathVariable ObjectId myId){
        Optional<User> user = userService.GetInfoById(myId);
        if (user.isPresent()){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> Save(@RequestBody User userInfo){
        String flag = userService.SaveUserInfo(userInfo);
        if (flag.equals("saved")){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> DeleteById(@PathVariable ObjectId myId){
        String flag = userService.DeleteUserInfoByID(myId);
        if (flag.equals("deleted")){
            return new ResponseEntity<>(HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> EditUserInfoById(@RequestBody User newUserInfo,@PathVariable String userName){
        User oldUserInfo = userService.FindUserByUsername(userName);
        if (oldUserInfo != null){
            oldUserInfo.setUserName(newUserInfo.getUserName());
            oldUserInfo.setPassword(newUserInfo.getPassword());
            oldUserInfo.setItemList(newUserInfo.getItemList());
            userService.SaveUserInfo(oldUserInfo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
