package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ToDoMemoryRepositoryTest {
//    @Autowired
//    ToDoMemoryRepository toDoMemoryRepository;
//
//    @AfterEach
//    void clear(){
//        toDoMemoryRepository.clearStore();
//    }
//
//    @Test
//    @DisplayName("저장소 확인")
//    void repositoryCheck() {
//        ToDoItem toDoItem1 = new ToDoItem("1");
//        ToDoItem toDoItem2 = new ToDoItem("2");
//        ToDoItem toDoItem3 = new ToDoItem("3");
//        toDoMemoryRepository.save(toDoItem1);
//        toDoMemoryRepository.save(toDoItem2);
//        toDoMemoryRepository.save(toDoItem3);
//        List<ToDoItem> items = toDoMemoryRepository.findAll();
//        Assertions.assertThat(items.size()).isEqualTo(3);
//    }
}