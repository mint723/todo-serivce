package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

public interface ToDoRepository {
    ToDoItem save(ToDoItem toDoItem);

    ToDoItem findByNo(Long no);

    List<ToDoItem> findAll();

    void contextUpdate(Long no, String context);

    void statusUpdate(Long no);

    void deleteItem(Long no);

}
