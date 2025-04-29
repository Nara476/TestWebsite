package com.jeeshantraders.jeeshanTraders.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jeeshantraders.jeeshanTraders.Entities.Items;

@Repository
public interface ItemsRepository extends MongoRepository<Items,ObjectId>{
    
}
