package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@org.springframework.stereotype.Service
public class ItemService {
    private ItemDAO itemDAO;

    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public void delete(Long id) {
        itemDAO.delete(id);
    }

    public void deleteByName(String name) throws SQLException {
        itemDAO.delete(name);
    }

    public void update(Item item) {
        itemDAO.update(item);
    }

    public Item findById(Long id) {
        return itemDAO.findById(id);
    }
}
