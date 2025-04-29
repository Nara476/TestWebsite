package com.jeeshantraders.jeeshanTraders.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeeshantraders.jeeshanTraders.Entities.Items;
import com.jeeshantraders.jeeshanTraders.Services.ItemService;

import jakarta.validation.Valid;

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


@RestController
@RequestMapping("/home")
public class ItemController {

    
    @Autowired 
    private ItemService itemService;

    
    @GetMapping
    public ResponseEntity<?> GetData(){
        List<Items> items = itemService.ShowData();
        if (!items.isEmpty() && items != null){
            return new ResponseEntity<>(items,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Items> GetDataByID(@PathVariable ObjectId id){
        Optional<Items> item= itemService.ShowItemById(id);
        if (item.isPresent()){
            return new ResponseEntity<>(item.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> CreateData(@RequestBody Items item,@PathVariable String userName){

        if (itemService.SaveData(item,userName).equals("success")){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> DeleteData(@PathVariable ObjectId id,@PathVariable String userName){
        if (itemService.DeleteItemById(id,userName).equals("deleted")){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> UpdateData(@PathVariable ObjectId id,@Valid @RequestBody Items newItem,@PathVariable String userName){
        Items old = itemService.ShowItemById(id).orElse(null);
        if (old != null){
            old.setName(newItem.getName() != null && !newItem.getName().equals("")? newItem.getName() : old.getName());
            old.setBrandName(newItem.getBrandName()!= null && !newItem.getBrandName().equals("") ? newItem.getBrandName() : old.getBrandName());
            if (newItem.getPrice() != null) {
                old.setPrice(newItem.getPrice());
            }
            if (newItem.getQuantity() != null) {
                old.setQuantity(newItem.getQuantity());
            }
            itemService.SaveData(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
}
