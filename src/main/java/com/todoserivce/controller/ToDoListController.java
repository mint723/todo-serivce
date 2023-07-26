package com.todoserivce.controller;


import com.todoserivce.domain.todo.ToDoItem;
import com.todoserivce.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/basic")
@RequiredArgsConstructor
public class ToDoListController {

//    @Autowired
//    private DataSource dataSource;
//
//    ToDoRepository toDoRepository = new ToDoDbRepository(dataSource);

    private final ToDoRepository toDoRepository;

    @GetMapping("/toDoItems")
    public String getToDoItems(Model model){
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        model.addAttribute("toDoItems",toDoItems);
        return "basic/toDoItems";
    }

    @PostMapping("/toDoItems")
    public String postToDoItems(@ModelAttribute ToDoItem toDoItem, Model model) throws SQLException {
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
