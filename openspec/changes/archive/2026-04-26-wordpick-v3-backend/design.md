## Context

taotao-tool 后端已具备完整的登录体系（Cookie token + user_id + `@RequireLogin` 注解），wordpick 词库相关表（wp_category、wp_chapter、wp_word 等）已上线。v3 在此基础上新增用户学习状态维度。

## Goals / Non-Goals

**Goals:**
- 新增单词掌握状态标记（认识/不认识），按类别隔离
- 支持按类别查询生词本（state=1 的词）

**Non-Goals:**
- 不引入测验功能（从简，后续版本再做）
- 不引入艾宾浩斯复习调度
- 不做用户注册，继续沿用邀请制入库

## Decisions

**1. wp_user_word_mark 冗余 category_id 和 chapter_id**

生词本按类别查询是高频场景，直接冗余避免多表 join。word_id 变更类别的情况不存在（词库数据静态），冗余数据不会失效。

**2. 接口动词前缀命名风格**

与现有 wp 前缀 Controller 风格保持一致：`WpWordController`，接口命名为 saveMark / listMark。

## Risks / Trade-offs

- [冗余字段] category_id / chapter_id 与 wp_word 存在隐式依赖 → 词库数据静态，风险可控

## 新增表 DDL

```sql
CREATE TABLE wp_user_word_mark (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id      INT NOT NULL,
  word_id      BIGINT NOT NULL,
  category_id  INT NOT NULL,
  state        TINYINT NOT NULL COMMENT '1:不认识 2:认识',
  created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_user_word (user_id, word_id)
);
```
