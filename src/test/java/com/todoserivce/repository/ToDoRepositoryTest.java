package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ToDoRepositoryTest {
    @Autowired
    ToDoRepository toDoRepository;

    @AfterEach
    void clear(){
        toDoRepository.clearStore();
    }

    @Test
    @DisplayName("저장소 확인")
    void repositoryCheck() {
        ToDoItem toDoItem1 = new ToDoItem("1", 'N');
        ToDoItem toDoItem2 = new ToDoItem("2", 'N');
        ToDoItem toDoItem3 = new ToDoItem("3", 'N');
        toDoRepository.save(toDoItem1);
        toDoRepository.save(toDoItem2);
        toDoRepository.save(toDoItem3);
        List<ToDoItem> items = toDoRepository.findAll();
        Assertions.assertThat(items.size()).isEqualTo(3);
    }
}