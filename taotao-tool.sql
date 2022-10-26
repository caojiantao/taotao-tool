SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `cover_id` int NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for album_file
-- ----------------------------
DROP TABLE IF EXISTS `album_file`;
CREATE TABLE `album_file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `album_id` int NOT NULL,
  `file_id` int NOT NULL,
  `file_type` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_album_id` (`album_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `filename` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `md5` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `bytes` bigint NOT NULL,
  `file_type` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `mime_type` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `ext` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_filename` (`filename`) USING BTREE,
  UNIQUE KEY `uniq_md5` (`md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
