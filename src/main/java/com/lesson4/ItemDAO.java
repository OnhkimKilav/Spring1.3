package com.lesson4;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Item save(Item item){
        entityManager.persist(item);
        return item;
    }

    public Item findById(Long id){
        Item item = entityManager.find(Item.class, id);
        return item;
    }

    public void delete(Long id){
        entityManager.remove(findById(id));
    }

    public void update(Item item){
        entityManager.merge(item);
    }
}
