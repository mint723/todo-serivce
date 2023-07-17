package com.todoserivce.domain.todo;

import lombok.Data;

@Data
public class ToDoItem {
    private Long no;
    private String content;
    private Character status;

    public ToDoItem(String content, Character status) {
        this.content = content;
        this.status = status;
    }
}
