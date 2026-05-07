package com.taotao.tool.wordpick.event;

import com.taotao.tool.wordpick.model.WpWordAction;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 用户单词行为事件，用于触发用户章节学习摘要等派生数据的增量更新。
 */
@Getter
public class WordActionEvent extends ApplicationEvent {

    private final WpWordAction newAction;

    public WordActionEvent(Object source, WpWordAction newAction) {
        super(source);
        this.newAction = newAction;
    }
}
