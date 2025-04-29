package com.jeeshantraders.jeeshanTraders.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jeeshantraders.jeeshanTraders.Entities.Items;
import com.jeeshantraders.jeeshanTraders.Entities.User;
import com.jeeshantraders.jeeshanTraders.Repository.ItemsRepository;
import com.jeeshantraders.jeeshanTraders.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ItemsRepository itemRepo;

    public List<User> GetInfo(){
        return userRepo.findAll();
    }

    public Optional<User> GetInfoById(ObjectId myId){
        return userRepo.findById(myId);
    }

    public String SaveUserInfo(User userInfo){
        List<Items> savedItems = new ArrayList<>();
        for (Items item : userInfo.getItemList()){
            Items iteratedItem = itemRepo.save(item);
            savedItems.add(iteratedItem);
        }
        userInfo.setItemList(savedItems);
        userRepo.save(userInfo);
        return "saved";
    }

    public String DeleteUserInfoByID(ObjectId myId){
        userRepo.deleteById(myId);
        return "deleted";
    }

    public User FindUserByUsername(String userName){
        return userRepo.findByUserName(userName);
    }
}
