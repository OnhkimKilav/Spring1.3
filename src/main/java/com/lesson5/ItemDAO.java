package com.lesson5;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void delete(String name) throws SQLException {
        Query query = entityManager.createNativeQuery("DELETE FROM ITEM WHERE NAME = ?").setParameter(1, name);
        query.executeUpdate();
    }

    public void update(Item item){
        entityManager.merge(item);
    }
}
