# word-mark Specification

## Purpose
TBD - created by archiving change wordpick-v3-backend. Update Purpose after archive.
## Requirements
### Requirement: 标记单词掌握状态
The system SHALL allow authenticated users to mark a word as 认识（state=2）or 不认识（state=1）via upsert semantics. The mark is scoped to the word's category.

#### Scenario: 首次标记不认识
- **WHEN** 用户在滑卡中点击「不认识」
- **THEN** 系统在 `wp_user_word_mark` 中插入一条记录，state=1，冗余 category_id

#### Scenario: 重复标记（覆盖）
- **WHEN** 用户对同一 word_id 再次标记（认识或不认识）
- **THEN** 系统更新已有记录的 state 和 updated_time，不插入新记录

#### Scenario: 未登录用户标记
- **WHEN** 未登录用户调用标记接口
- **THEN** 系统返回未登录错误，不写入任何数据

### Requirement: 查询生词本
The system SHALL return all words marked as 不认识（state=1）for a given category, for the current authenticated user.

#### Scenario: 正常查询
- **WHEN** 已登录用户请求 `GET /wp/word/listMark?categoryId=`
- **THEN** 系统返回该类别下 state=1 的词列表，含单词、音标、词义、例句

#### Scenario: 生词本为空
- **WHEN** 用户在该类别下无不认识的词
- **THEN** 系统返回空列表，不报错

