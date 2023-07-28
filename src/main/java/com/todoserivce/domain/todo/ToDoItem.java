package com.todoserivce.domain.todo;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "todoitem")
@Data
public class ToDoItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    private String context;
    private Boolean status;

    public ToDoItem(String context) {
        this.context = context;
    }

    public ToDoItem() {
    }
}
