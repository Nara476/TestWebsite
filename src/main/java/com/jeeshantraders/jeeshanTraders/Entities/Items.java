package com.jeeshantraders.jeeshanTraders.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Document(collection = "Items")
@Data
public class Items {
    
    @Id
    private ObjectId id;

    @NotNull(message = "name cannot be empty")
    private String name;

    @NotNull(message = "price cannot be empty")
    private Double price;
    
    @NotNull(message = "quantity cannot be empty")
    private Integer quantity;

    @NotNull(message = "brandName cannot be empty")
    private String brandName;
}
