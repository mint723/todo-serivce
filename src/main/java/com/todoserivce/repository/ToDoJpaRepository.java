package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class ToDoJpaRepository implements ToDoRepository{

    private final EntityManager em;
    @Transactional
    @Override
    public ToDoItem save(ToDoItem toDoItem) {
        toDoItem.setStatus(false);
        em.persist(toDoItem);
        return toDoItem;
    }
    @Override
    public ToDoItem findByNo(Long no) {
        ToDoItem toDoItem = em.find(ToDoItem.class, no);
        return toDoItem;
    }

    @Override
    public List<ToDoItem> findAll() {
        return em.createQuery("select m from todoitem m", ToDoItem.class).
                getResultList();
    }

    @Transactional
    @Override
    public void contextUpdate(Long no, String context) {
        ToDoItem toDoItem = findByNo(no);
        toDoItem.setContext(context);
        em.merge(toDoItem);
    }

    @Transactional
    @Override
    public void statusUpdate(Long no) {
        ToDoItem toDoItem = findByNo(no);
        toDoItem.setStatus(!toDoItem.getStatus());
        em.merge(toDoItem);
    }
    @Transactional
    @Override
    public void deleteItem(Long no) {
        ToDoItem toDoItem = em.find(ToDoItem.class, no);
        if(toDoItem!=null){
            em.remove(toDoItem);
        }
    }
}
