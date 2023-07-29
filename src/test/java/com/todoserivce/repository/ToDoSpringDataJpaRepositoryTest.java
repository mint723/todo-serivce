package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ToDoSpringDataJpaRepositoryTest {

    @Autowired
    ToDoRepository toDoRepository;
    @Test
    void 저장() {
        ToDoItem toDoItem = new ToDoItem("안녕");
        ToDoItem save = toDoRepository.save(toDoItem);
        Long no = save.getNo();
        Assertions.assertThat(toDoRepository.findByNo(no)).isEqualTo(toDoItem);

    }
}