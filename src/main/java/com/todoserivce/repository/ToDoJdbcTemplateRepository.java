package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ToDoJdbcTemplateRepository implements ToDoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ToDoItem save(ToDoItem toDoItem){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("toDoItem").usingGeneratedKeyColumns("no");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("context",toDoItem.getContext());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        toDoItem.setNo(key.longValue());
        toDoItem.setStatus(false);
        return toDoItem;
    }

    @Override
    public ToDoItem findByNo(Long no) {
        List<ToDoItem> toDoItems = jdbcTemplate.query("select * from toDoItem where no ="+no, toDoItemRowMapper());
        return toDoItems.iterator().next();
    }

    @Override
    public List<ToDoItem> findAll() {
        return jdbcTemplate.query("select * from toDoItem",toDoItemRowMapper());
    }

    @Override
    public void contextUpdate(Long no, String context) {
        jdbcTemplate.update("update toDoItem set context=\""+context+"\" where no="+no);
    }

    @Override
    public void statusUpdate(Long no) {
        ToDoItem toDoItem = findByNo(no);
        jdbcTemplate.update("update toDoItem set status="+!toDoItem.getStatus()+" where no="+no);
    }

    @Override
    public void deleteItem(Long no) {
        jdbcTemplate.update("delete from toDoItem where no ="+no);
    }

    private RowMapper<ToDoItem> toDoItemRowMapper(){
        return (rs, rowNum) -> {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setNo(rs.getLong("no"));
            toDoItem.setContext(rs.getString("context"));
            toDoItem.setStatus(rs.getBoolean("status"));
            return toDoItem;
        };
    }
}
