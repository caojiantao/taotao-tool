## Why

拾词 v2 仅支持匿名浏览词库，缺乏用户维度的学习状态记录；v3 引入用户域，支持单词标记（认识/不认识）和生词本功能。

## What Changes

- 新增 `wp_user_word_mark` 表，记录用户对每个单词的掌握状态（不认识/认识），upsert 语义
- 新增 `WpWordController`，提供单词标记和生词本查询接口

## Capabilities

### New Capabilities

- `word-mark`: 用户对单词标记认识/不认识，支持按类别查询不认识的词（生词本）

### Modified Capabilities

（无）

## Impact

- **新增表**：`wp_user_word_mark`
- **新增 Controller**：`WpWordController`（`/wp/word`）
- **依赖**：复用 taotao-tool 现有登录体系（Cookie token + user_id），相关接口需加 `@RequireLogin`
- **后端项目**：taotao-tool
