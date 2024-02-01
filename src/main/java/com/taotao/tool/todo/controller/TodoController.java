package com.taotao.tool.todo.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.todo.dto.TodoDayGroupDTO;
import com.taotao.tool.todo.model.Todo;
import com.taotao.tool.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
        if (Objects.equals(groupId, 10)) {
            // 今日待办，需要查询所有组下的未完成的，和今日已完成的
            List<Todo> todoList = todoService.query()
                    .list();
            LocalDate now = LocalDate.now();
            todoList.removeIf(item -> !Objects.equals(now, item.getUpdateTime().toLocalDate()) && Objects.equals(1, item.getState()));
            TodoDayGroupDTO dto = new TodoDayGroupDTO(null, JsonUtils.convert(todoList, TodoDayGroupDTO.Item.class));
            list.add(dto);
            return ApiResult.success(list);
        } else if (Objects.equals(groupId, 11)) {
            // 已完成，需要按日期倒序进行分组返回
            List<Todo> todoList = todoService.query()
                    .eq("state", 1)
                    .orderByDesc("update_time")
                    .list();
            Map<LocalDate, TodoDayGroupDTO> groupMap = new HashMap<>();
            for (Todo todo : todoList) {
                LocalDate localDate = todo.getUpdateTime().toLocalDate();
                if (!groupMap.containsKey(localDate)) {
                    TodoDayGroupDTO groupDTO = new TodoDayGroupDTO(localDate.toString(), new ArrayList<>());
                    groupMap.put(localDate, groupDTO);
                    list.add(groupDTO);
                }
                TodoDayGroupDTO groupDTO = groupMap.get(localDate);
                groupDTO.getItemList().add(JsonUtils.convert(todo, TodoDayGroupDTO.Item.class));
            }
            return ApiResult.success(list);
        } else {
            // 其他组，正常查未完成的和今日完成的
            List<Todo> todoList = todoService.query()
                    .eq("group_id", groupId)
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
