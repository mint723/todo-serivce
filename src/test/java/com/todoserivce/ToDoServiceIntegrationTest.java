package com.todoserivce;

import com.todoserivce.domain.todo.ToDoItem;
import com.todoserivce.repository.ToDoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@SpringBootTest
@Transactional
public class ToDoServiceIntegrationTest {


    @Autowired
    ToDoRepository toDoRepository;

    @Test
    void 일정등록(){
        //given
        ToDoItem toDoItem = new ToDoItem("일정 테스트");
        //when
        ToDoItem save = toDoRepository.save(toDoItem);
        Long saveNo = save.getNo();
        //then
        Assertions.assertThat(save).isEqualTo(toDoRepository.findByNo(saveNo));
    }
}
