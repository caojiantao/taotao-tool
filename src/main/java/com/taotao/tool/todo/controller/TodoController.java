package com.taotao.tool.todo.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.todo.model.Todo;
import com.taotao.tool.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caojiantao
 * @since 2024-01-30
 */
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @GetMapping("/listTodo")
    public ApiResult<List<Todo>> listTodo(Integer groupId) {
        List<Todo> list = todoService.query()
                .eq("group_id", groupId)
                .list();
        return ApiResult.success(list);
    }

    @PostMapping("/saveTodo")
    public ApiResult<Void> saveTodo(@RequestBody Todo todo) {
        todoService.save(todo);
        return ApiResult.success();
    }

    @PostMapping("/updateTodo")
    public ApiResult<Void> updateTodo(Integer id, Integer newState) {
        todoService.update()
                .eq("id", id)
                .set("state", newState)
                .update();
        return ApiResult.success();
    }

    @PostMapping("/deleteTodo")
    public ApiResult<Void> deleteTodo(Integer id) {
        todoService.removeById(id);
        return ApiResult.success();
    }
}
