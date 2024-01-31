package com.taotao.tool.todo.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.todo.dto.TodoDayGroupDTO;
import com.taotao.tool.todo.model.Todo;
import com.taotao.tool.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public ApiResult<List<TodoDayGroupDTO>> listTodo(Integer groupId) {
        List<TodoDayGroupDTO> list = new ArrayList<>();
        if (Objects.equals(groupId, 11)) {
            // 已完成，需要按日期进行分组返回
            Map<LocalDate, List<TodoDayGroupDTO.Item>> map = todoService.query()
                    .eq("state", 1)
                    .orderByDesc("update_time")
                    .list()
                    .stream()
                    .map(item -> JsonUtils.convert(item, TodoDayGroupDTO.Item.class))
                    .collect(Collectors.groupingBy(item -> item.getUpdateTime().toLocalDate()));
            List<LocalDate> dayList = map.keySet().stream().sorted().collect(Collectors.toList());
            for (LocalDate localDate : dayList) {
                TodoDayGroupDTO dto = new TodoDayGroupDTO(localDate.toString(), map.get(localDate));
                list.add(dto);
            }
            return ApiResult.success(list);
        } else {
            // 非已完成的，正常就一个组，但是需要过滤掉非今天完成的
            List<Todo> todoList = todoService.query()
                    .eq("group_id", groupId)
                    .orderByDesc("update_time")
                    .list();
            LocalDate now = LocalDate.now();
            todoList.removeIf(item -> !Objects.equals(now, item.getUpdateTime().toLocalDate()) && Objects.equals(1, item.getState()));
            TodoDayGroupDTO dto = new TodoDayGroupDTO(null, JsonUtils.convert(todoList, TodoDayGroupDTO.Item.class));
            list.add(dto);
            return ApiResult.success(list);
        }
    }

    @PostMapping("/saveTodo")
    public ApiResult<Void> saveTodo(@RequestBody Todo todo) {
        todoService.saveOrUpdate(todo);
        return ApiResult.success();
    }

    @PostMapping("/deleteTodo")
    public ApiResult<Void> deleteTodo(Integer id) {
        todoService.removeById(id);
        return ApiResult.success();
    }
}
