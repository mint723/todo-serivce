package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ToDoMemoryRepository implements ToDoRepository {
    private static final Map<Long, ToDoItem> Store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public ToDoItem save(ToDoItem toDoItem){
        toDoItem.setNo(++sequence);
        toDoItem.setStatus(false);
        Store.put(toDoItem.getNo(), toDoItem);
        return toDoItem;
    }

    @Override
    public ToDoItem findByNo(Long no){
        return Store.get(no);
    }
    @Override

    public List<ToDoItem> findAll(){
        return new ArrayList<>(Store.values());
    }

    @Override
    public void contextUpdate(Long no, String context){
        ToDoItem preItem = Store.get(no);
        preItem.setContext(context);
    }

    @Override
    public void statusUpdate(Long no) {

    }

    @Override
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
