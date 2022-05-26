package org.sherlock.persists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
public class FruitRepository {

    private final Map<Long, Fruit> fruitMap = new ConcurrentHashMap<>();
    private final AtomicLong identify = new AtomicLong(0);

    public List<Fruit> findAll(){
        return new ArrayList<>(fruitMap.values());
    }

    public Fruit findById(long id){
        return fruitMap.get(id);
    }

    public void insert(Fruit fruit){
        long id = identify.incrementAndGet();
        fruit.setId(id);
        fruitMap.put(id, fruit);
    }

    public void update(Fruit fruit) {
        fruitMap.put(fruit.getId(), fruit);
    }

    public void delete(long id) {
        fruitMap.remove(id);
    }
}
