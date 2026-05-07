SET NAMES utf8mb4;

-- 类别（小学、CET4、考研…）
CREATE TABLE `wp_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '类别名称',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- 章节（属于某个类别，如 Unit 1）
CREATE TABLE `wp_chapter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL COMMENT '所属类别',
  `name` varchar(100) NOT NULL COMMENT '章节名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- 单词（属于某个章节，词条按词书独立维护）
CREATE TABLE `wp_word` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chapter_id` int NOT NULL COMMENT '所属章节',
  `word` varchar(100) NOT NULL COMMENT '单词',
  `phonetic_us` varchar(100) DEFAULT NULL COMMENT '美式音标',
  `phonetic_uk` varchar(100) DEFAULT NULL COMMENT '英式音标',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- 释义（属于某个单词，可多条）
CREATE TABLE `wp_word_sense` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word_id` int NOT NULL COMMENT '所属单词',
  `pos` varchar(20) DEFAULT NULL COMMENT '词性（n./v./adj.…）',
  `meaning` varchar(500) NOT NULL COMMENT '中文释义',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- 例句（属于某个单词，可多条）
CREATE TABLE `wp_word_example` (
  `id` int NOT NULL AUTO_INCREMENT,
  `word_id` int NOT NULL COMMENT '所属单词',
  `sentence` varchar(500) NOT NULL COMMENT '英文例句',
  `translation` varchar(500) DEFAULT NULL COMMENT '中文翻译',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- 用户单词标记（记录用户对每个单词的掌握状态）
CREATE TABLE `wp_user_word_mark` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户 ID',
  `word_id` int NOT NULL COMMENT '单词 ID',
  `category_id` int NOT NULL COMMENT '所属类别 ID（冗余，加速生词本查询）',
  `state` tinyint NOT NULL COMMENT '掌握状态：1=不认识 2=认识',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_word` (`user_id`, `word_id`)
);

-- 用户单词行为流水（事件溯源原表，用户章节学习摘要等上层数据由此推导）
CREATE TABLE `wp_word_action` (
  `id`         bigint   NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id`    int      NOT NULL                COMMENT '用户ID',
  `learn_session_id` bigint DEFAULT NULL         COMMENT '学习会话ID',
  `word_id`    int      NOT NULL                COMMENT '单词ID（wp_word.id）',
  `action`     smallint NOT NULL                COMMENT '行为类型，分段编码：1x=判断类（11=认识 12=不认识）',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '行为时间',
  PRIMARY KEY (`id`),
  KEY `idx_learn_session` (`learn_session_id`),
  KEY `idx_user_word`    (`user_id`, `word_id`),
  KEY `idx_user_created` (`user_id`, `created_at`)
);

-- 用户学习会话（一次学习流的开始、结束与稳定汇总）
CREATE TABLE `wp_learn_session` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户ID',
  `started_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `finished_at` datetime DEFAULT NULL COMMENT '结束时间',
  `duration_seconds` int DEFAULT NULL COMMENT '本次学习时长（秒）',
  `known_action_count` int NOT NULL DEFAULT 0 COMMENT '本次认识次数（总）',
  `known_word_count` int NOT NULL DEFAULT 0 COMMENT '本次认识词数（去重）',
  `unknown_action_count` int NOT NULL DEFAULT 0 COMMENT '本次不认识次数（总）',
  `unknown_word_count` int NOT NULL DEFAULT 0 COMMENT '本次不认识词数（去重）',
  `state` tinyint NOT NULL DEFAULT 1 COMMENT '状态：1=进行中 2=已完成',
  PRIMARY KEY (`id`),
  KEY `idx_user_started` (`user_id`, `started_at`)
);
