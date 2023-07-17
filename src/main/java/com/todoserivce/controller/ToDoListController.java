package com.todoserivce.controller;


import com.todoserivce.domain.todo.ToDoItem;
import com.todoserivce.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic")
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoRepository toDoRepository;

    @GetMapping("/toDoItems")
    public String GetToDoItems(Model model){
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        model.addAttribute("toDoItems",toDoItems);
        return "basic/toDoItems";
    }
    @PostMapping("/toDoItems")
    public String PostToDoItems(@RequestParam String context, Model model){
        toDoRepository.save(new ToDoItem(context,'N'));
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        model.addAttribute("toDoItems",toDoItems);
        return "redirect:/basic/toDoItems";
    }


}
