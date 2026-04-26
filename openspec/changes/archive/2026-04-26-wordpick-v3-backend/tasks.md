## 1. 数据库

- [ ] 1.1 执行 DDL：创建 wp_user_word_mark 表

## 2. word-mark 能力

- [ ] 2.1 新增 WpUserWordMark 实体类及 Mapper
- [ ] 2.2 新增 WpWordController，实现 POST /wp/word/saveMark（upsert，加 @RequireLogin）
- [ ] 2.3 实现 GET /wp/word/listMark?categoryId=（返回 state=1 的词含词义例句，加 @RequireLogin）
