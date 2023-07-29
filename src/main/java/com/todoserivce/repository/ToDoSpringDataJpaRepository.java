package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
public interface ToDoSpringDataJpaRepository extends JpaRepository<ToDoItem,Long>{
}
