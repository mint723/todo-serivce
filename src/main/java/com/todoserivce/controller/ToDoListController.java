package com.todoserivce.controller;


import com.todoserivce.domain.todo.ToDoItem;
import com.todoserivce.repository.ToDoDbRepository;
import com.todoserivce.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@Controller
@RequestMapping("/basic")
public class ToDoListController {

//    @Autowired
//    private DataSource dataSource;
//
//    ToDoRepository toDoRepository = new ToDoDbRepository(dataSource);

    @Autowired
    private final ToDoRepository toDoRepository;

    public ToDoListController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/toDoItems")
    public String getToDoItems(Model model){
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        model.addAttribute("toDoItems",toDoItems);
        return "basic/toDoItems";
    }

    @PostMapping("/toDoItems")
    public String postToDoItems(@ModelAttribute ToDoItem toDoItem, Model model){
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

    @PostMapping("statusUpdate/{toDoItemNo}")
    public String done(@PathVariable Long toDoItemNo){
        toDoRepository.statusUpdate(toDoItemNo);
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
        toDoRepository.contextUpdate(toDoItemNo,context);
        return "redirect:/basic/toDoItems";
    }
}
