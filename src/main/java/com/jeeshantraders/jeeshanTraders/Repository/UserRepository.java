package com.jeeshantraders.jeeshanTraders.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jeeshantraders.jeeshanTraders.Entities.User;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User,ObjectId>{
    public User findByUserName(String userName);
}
