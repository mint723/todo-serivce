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
    public String PostToDoItems(@ModelAttribute ToDoItem toDoItem, Model model){
        toDoRepository.save(toDoItem);
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        model.addAttribute("toDoItems",toDoItems);
        return "redirect:/basic/toDoItems";
    }

    @PostMapping("delete/{toDoItemNo}")
    public String delete(@PathVariable Long toDoItemNo, Model model){
        toDoRepository.deleteItem(toDoItemNo);
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        model.addAttribute("toDoItems",toDoItems);
        return "redirect:/basic/toDoItems";
    }

    @PostMapping("status/{toDoItemNo}")
    public String done(@PathVariable Long toDoItemNo, Model model){
        ToDoItem toDoItem = toDoRepository.findByNo(toDoItemNo);
        toDoItem.setStatus(!toDoItem.getStatus());
        return "redirect:/basic/toDoItems";
    }

    @PostMapping("update/{toDoItemNo}")
    public String update(@PathVariable Long toDoItemNo, Model model){
        ToDoItem toDoItem = toDoRepository.findByNo(toDoItemNo);
        model.addAttribute("toDoItem",toDoItem);
        return "basic/toDoItem";
    }

    @PostMapping("/toDoItem/{toDoItemNo}")
    public String updateContext(@PathVariable Long toDoItemNo, @RequestParam String context){
        ToDoItem toDoItem = toDoRepository.findByNo(toDoItemNo);
        toDoItem.setContext(context);
        System.out.println(context);
        return "redirect:/basic/toDoItems";
    }




}
