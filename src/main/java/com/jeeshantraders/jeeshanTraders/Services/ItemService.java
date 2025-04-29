package com.jeeshantraders.jeeshanTraders.Services;

import java.util.List;
import java.util.Optional;
import com.jeeshantraders.jeeshanTraders.Entities.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jeeshantraders.jeeshanTraders.Entities.Items;
import com.jeeshantraders.jeeshanTraders.Repository.ItemsRepository;
import com.jeeshantraders.jeeshanTraders.Repository.UserRepository;

@Service
public class ItemService {
    
    @Autowired
    private ItemsRepository itemRepo;

    @Autowired
    private UserService userService;

    public String SaveData(Items item,String userName){
        User user = userService.FindUserByUsername(userName);
        Items saved = itemRepo.save(item);
        user.getItemList().add(saved);
        userService.SaveUserInfo(user);
        
        return "success";
    }

    public String SaveData(Items item){
        itemRepo.save(item);
        return "success";
    }

    public List<Items> ShowData(){
        return itemRepo.findAll();
    }

    public Optional<Items> ShowItemById(ObjectId id){
        return itemRepo.findById(id);
    }

    public String DeleteItemById(ObjectId id, String userName){
        User user = userService.FindUserByUsername(userName);
        user.getItemList().removeIf(x -> x.getId().equals(id));
        userService.SaveUserInfo(user);
        itemRepo.deleteById(id);
        return "deleted";
    }
}
