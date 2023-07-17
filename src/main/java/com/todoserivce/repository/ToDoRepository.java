package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ToDoRepository {
    private static final Map<Long, ToDoItem> Store = new HashMap<>();
    private static Long sequence = 0L;


    public ToDoItem save(ToDoItem toDoItem){
        toDoItem.setNo(++sequence);
        toDoItem.setStatus(false);
        Store.put(toDoItem.getNo(), toDoItem);
        return toDoItem;
    }

    public ToDoItem findByNo(Long no){
        return Store.get(no);
    }

    public List<ToDoItem> findAll(){
        return new ArrayList<>(Store.values());
    }

    public void update(Long no, ToDoItem toDoItem){
        ToDoItem preItem = Store.get(no);
        preItem.setStatus(toDoItem.getStatus());
        preItem.setContext(toDoItem.getContext());
    }

    public void deleteItem(Long no){
        int itemSize = Store.size();
        for (Long i = no; i < itemSize; i++) {
            ToDoItem toDoItem = Store.get(i + 1);
            toDoItem.setNo(i);
            Store.put(toDoItem.getNo(), toDoItem);
        }
        Store.remove((long)itemSize);
        sequence--;
    }

    public void clearStore(){
        Store.clear();
        sequence=0L;
    }

}
