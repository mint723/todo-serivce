package com.todoserivce.domain.todo;

import lombok.Data;

@Data
public class ToDoItem {
    private Long no;
    private String context;
    private Boolean status;

    public ToDoItem(String context) {
        this.context = context;
    }

    public ToDoItem() {
    }
}
