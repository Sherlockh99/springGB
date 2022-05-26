package org.sherlock.persists;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Fruit {
    @Setter
    private Long id;
    private final String fruitName;

    public Fruit(String fruitName){
        this.fruitName = fruitName;
    }
}
