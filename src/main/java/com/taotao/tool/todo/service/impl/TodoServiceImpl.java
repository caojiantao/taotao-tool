package com.taotao.tool.todo.service.impl;

import com.taotao.tool.todo.model.Todo;
import com.taotao.tool.todo.mapper.TodoMapper;
import com.taotao.tool.todo.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojiantao
 * @since 2024-01-30
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

}
